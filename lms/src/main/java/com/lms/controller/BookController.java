package com.lms.controller;

import static com.lms.utils.constants.UrlMappingConstant.BOOK_PATH;
import static com.lms.utils.constants.UrlMappingConstant.INDEX_PATH;
import static com.lms.utils.constants.UrlMappingConstant.SAVE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.BOOK_VIEW_BY_ID_PATH;
import static com.lms.utils.constants.UrlMappingConstant.CREATE_PATH;
import static com.lms.utils.constants.ViewConstant.BOOK_INDEX_VIEW;
import static com.lms.utils.constants.ViewConstant.BOOK_VIEW;
import static com.lms.utils.constants.ViewConstant.BOOK_CREATE_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_BOOK_INDEX;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.Book;
import com.lms.models.Category;
import com.lms.models.Library;
import com.lms.services.book.BookService;
import com.lms.services.category.CategoryService;
import com.lms.utils.customannotation.annotaion.XxsFilter;
import com.lms.config.factory.ImageFactory;
import com.lms.services.library.LibraryService;
import com.lms.utils.beans.BookBean;
import com.lms.utils.beans.CategoryBean;
import com.lms.utils.helper.SecurityUtil;

/**
 * Created by bhushan on 17/4/17.
 */
@Controller
@RequestMapping(value = BOOK_PATH)
@PreAuthorize("isAuthenticated()")
@Slf4j
public class BookController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private ImageFactory imageFactory;
    @Autowired
    private MessageSource messageSource;

    @Value("${lcm.customer.support.email}")
    private String supportEmail;
    @Value("${localstroge.path}")
    private String filePath;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "categoryBeens", new CustomCollectionEditor(Set.class)
        {
            @Override
            protected Object convertElement(Object element)
            {
                Long id = null;

                if(element instanceof String && !((String)element).equals("")){
                    try{
                        id = Long.parseLong((String) element);
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                else if(element instanceof Long) {
                    id = (Long) element;
                }

                if (id != null) {
                    return CategoryBean.builder().id(id).build();
                }
                return null;
            }
        });
    }

    @RequestMapping(INDEX_PATH)
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        ModelAndView modelAndView = new ModelAndView(BOOK_INDEX_VIEW);
        if (currentPageNumber == null) {
            currentPageNumber =1;
        }
        Page<Book> page = bookService.getPageRequest(currentPageNumber);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        modelAndView.addObject("books",  page.getContent());
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        return modelAndView;
    }

    @RequestMapping(BOOK_VIEW_BY_ID_PATH)
    public ModelAndView view(@PathVariable String id) {
        Book book = bookService.findByUuid(id);
        Library library = book.getLibrary();
        if(book.getImageUrl() != null) {
            book.setImageUrl(imageFactory.getSendContentService(library.getSaveImageServiceType()).resourceUrl(book.getImageUrl()));
        }
        ModelAndView modelAndView = new ModelAndView(BOOK_VIEW);
        modelAndView.addObject("book", book);
        modelAndView.addObject("categories", book.getCategories());
        return modelAndView;
    }


    @RequestMapping(CREATE_PATH)
    public ModelAndView create() {
        return getCreateModel(new BookBean());
    }

    @XxsFilter
    @RequestMapping(SAVE_PATH)
    public ModelAndView save(@RequestParam(value = "image", required = false) MultipartFile image, @Valid @ModelAttribute("book")BookBean bookBean, BindingResult result, Map map) {

        if (result.hasErrors()) {
            ModelAndView modelAndView = getCreateModel(bookBean);
            return modelAndView;
        }
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        ModelAndView createViewModel = getCreateModel(bookBean);
        Locale locale = LocaleContextHolder.getLocale();
        if (bookService.countBYLibraryAndIsbn(library, bookBean.getIsbn()) != 0) {
            createViewModel.addObject("error", messageSource.getMessage("book.already.exist", new Object[] {bookBean.getIsbn()}, locale));
            return createViewModel;
        }
        if(image != null && !image.isEmpty()) {
            try {
                String cloudinaryURL =imageFactory.getSendContentService(library.getSaveImageServiceType()).uploadImage(image);
                bookBean.setImageUrl(cloudinaryURL);

            } catch (Exception e) {
                log.error("Exception during upload image: {}", e);
                createViewModel.addObject("error",  messageSource.getMessage("something.went.wrong.during.upload.image", new Object[] {supportEmail}, locale));
            }
        }
        try {
            Book bookInstance = BookBean.convertBean(bookBean);
            if (bookBean.getCategoryBeens() != null) {
                List<Long> ids = bookBean.getCategoryBeens().stream().map(p -> p.getId()).collect(Collectors.toList());
                Iterable<Category>  categories = categoryService.getCategoryByIds(ids);
                if (categories != null) {
                    Set<Category> set = new HashSet<Category>((Collection)categories);
                    bookInstance.setCategories(set);
                }
                bookInstance.setLibrary(library);
                bookService.create(bookInstance);
            }
        } catch (Exception e) {

            createViewModel.addObject("error", e.getMessage());
            return createViewModel;
        }
        return new ModelAndView(REDIRECT_BOOK_INDEX);
    }

    private ModelAndView getCreateModel(BookBean bookBean) {
        ModelAndView modelAndView = new  ModelAndView(BOOK_CREATE_VIEW);
        modelAndView.addObject("book", bookBean);
        List<Category> categories = categoryService.getAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
}
