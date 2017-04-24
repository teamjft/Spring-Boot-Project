package com.lms.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import com.lms.utils.factory.ImageFactory;
import com.lms.services.library.LibraryService;
import com.lms.utils.beans.BookBean;
import com.lms.utils.beans.CategoryBean;

/**
 * Created by bhushan on 17/4/17.
 */
@Controller
@RequestMapping(value = "/book")
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

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        List<Book> books = bookService.getAll();
        ModelAndView modelAndView = new ModelAndView("book/index");
        modelAndView.addObject("books", books);

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

    @RequestMapping("/view/{id}")
    public ModelAndView view(@PathVariable String id) {
        Book book = bookService.findByUuid(id);
        Library library = book.getLibrary();
        if(book.getImageUrl() != null) {
            book.setImageUrl(imageFactory.getSendContentService(library.getSaveImageServiceType()).resourceUrl(book.getImageUrl()));
        }
        ModelAndView modelAndView = new ModelAndView("book/view");
        modelAndView.addObject("book", book);
        modelAndView.addObject("categories", book.getCategories());
        return modelAndView;
    }


    @RequestMapping("/create")
    public ModelAndView create() {
        return getCreateModel();
    }

    @RequestMapping("/save")
    public ModelAndView save(@RequestParam(value = "image", required = false) MultipartFile image, @Valid @ModelAttribute("book")BookBean bookBean, BindingResult result, Map model) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ModelAndView modelAndView = getCreateModel();
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        ModelAndView createViewModel = getCreateModel();
        if (bookService.countBYLibraryAndIsbn(library, bookBean.getIsbn()) != 0) {
            createViewModel.addObject("error", String.format("book already exist with isbn: %s", bookBean.getIsbn()));
            return createViewModel;
        }
        if(image != null && !image.isEmpty()) {
            try {
                String cloudinaryURL =imageFactory.getSendContentService(library.getSaveImageServiceType()).uploadImage(image);
                bookBean.setImageUrl(cloudinaryURL);

            } catch (Exception e) {
                log.error("Exception during upload image:"+e);
                createViewModel.addObject("error", "Something went wrong during upload image");
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
        return new ModelAndView("redirect:/book/index");
    }

    private ModelAndView getCreateModel() {
        ModelAndView modelAndView = new  ModelAndView("book/create");
        modelAndView.addObject("book", new BookBean());
        List<Category> categories = categoryService.getAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
}
