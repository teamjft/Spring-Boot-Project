package com.lms.controller;

import static com.lms.utils.constants.UrlMappingConstant.CATEGORY_PATH;
import static com.lms.utils.constants.UrlMappingConstant.CREATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.EDIT_PATH;
import static com.lms.utils.constants.UrlMappingConstant.INDEX_PATH;
import static com.lms.utils.constants.UrlMappingConstant.SAVE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.UPDATE_PATH;
import static com.lms.utils.constants.ViewConstant.CATEGORY_CREATE_VIEW;
import static com.lms.utils.constants.ViewConstant.CATEGORY_EDIT_VIEW;
import static com.lms.utils.constants.ViewConstant.CATEGORY_INDEX_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_CATEGORY_INDEX;

import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lms.models.Category;
import com.lms.services.category.CategoryService;
import com.lms.utils.beans.CategoryBean;
import com.lms.utils.customannotation.annotaion.XxsFilter;
import com.lms.utils.helper.PaginationHelper;

/**
 * Created by bhushan on 18/4/17.
 */
@Controller
@RequestMapping(value = CATEGORY_PATH)
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_LIBRARY_ADMIN')")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(INDEX_PATH)
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        Page<Category> page = categoryService.getPageRequest(currentPageNumber);
        return PaginationHelper.getModelAndView(CATEGORY_INDEX_VIEW, page, "categories");
    }

    @RequestMapping(EDIT_PATH)
    public ModelAndView edit(@PathVariable(value="id", required = false) String id) {
        Category category = categoryService.findByUuid(id);
        if(category != null) {
            CategoryBean categoryBean = CategoryBean.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .uuid(category.getUuid())
                    .build();
            ModelAndView modelAndView = getCreateOrEditModel(categoryBean, CATEGORY_EDIT_VIEW);
            return modelAndView;
        }
        return new ModelAndView(REDIRECT_CATEGORY_INDEX);
    }


    @RequestMapping(CREATE_PATH)
    public ModelAndView create() {
        CategoryBean categoryBean = new CategoryBean();
        return getCreateOrEditModel(categoryBean, CATEGORY_CREATE_VIEW);
    }

    @XxsFilter
    @RequestMapping(SAVE_PATH)
    public ModelAndView save(@Valid @ModelAttribute("category")CategoryBean categoryBean, BindingResult result, Map model) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = getCreateOrEditModel(categoryBean, CATEGORY_CREATE_VIEW);
            return modelAndView;
        }
        Locale locale = LocaleContextHolder.getLocale();
        long count = categoryService.getCountByName(categoryBean.getName());
        if(count == 0) {
            Category category = new Category();
            category.setName(categoryBean.getName());
            categoryService.create(category);
            ModelAndView modelAndView = new ModelAndView(REDIRECT_CATEGORY_INDEX);
            return modelAndView.addObject("success",  messageSource.getMessage("category.successfully.created", new Object[] {category.getName()}, locale));
        } else {
            ModelAndView modelAndView = getCreateOrEditModel(categoryBean, CATEGORY_CREATE_VIEW);
            modelAndView.addObject("error", messageSource.getMessage("category.duplicate.name",null, locale));
            return modelAndView;
        }

    }

    @XxsFilter
    @RequestMapping(UPDATE_PATH)
    public ModelAndView update(@Valid @ModelAttribute("category")CategoryBean categoryBean, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = getCreateOrEditModel(categoryBean, CATEGORY_EDIT_VIEW);
            return modelAndView;
        }
        Locale locale = LocaleContextHolder.getLocale();
        ModelAndView modelAndView = new ModelAndView(REDIRECT_CATEGORY_INDEX);
        Category category = categoryService.findByUuid(categoryBean.getUuid());
        if(category != null) {
            long count = categoryService.getCountByName(categoryBean.getName());
            if( count != 0) {
                ModelAndView mode = getCreateOrEditModel(categoryBean, CATEGORY_EDIT_VIEW);
                mode.addObject("error", messageSource.getMessage("category.duplicate.name",null, locale));
                return mode;
            }
            category.setName(categoryBean.getName());
            categoryService.update(category);
            return modelAndView.addObject("success", messageSource.getMessage("category.successfully.updated",null, locale));
        }
        modelAndView.addObject("error",  messageSource.getMessage("category.invalid",null, locale));
        return modelAndView;
    }

    private ModelAndView getCreateOrEditModel(CategoryBean categoryBean, String action) {
        ModelAndView modelAndView = new ModelAndView(action);
        modelAndView.addObject("category", categoryBean );
        return modelAndView;
    }

}
