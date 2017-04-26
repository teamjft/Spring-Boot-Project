package com.lms.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lms.models.Category;
import com.lms.services.category.CategoryService;
import com.lms.utils.beans.CategoryBean;

/**
 * Created by bhushan on 18/4/17.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        if (currentPageNumber == null) {
            currentPageNumber =1;
        }
        Page<Category> page = categoryService.getPageRequest(currentPageNumber);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        ModelAndView modelAndView = new ModelAndView("category/index");
        modelAndView.addObject("categories",  page.getContent());
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        return modelAndView;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(value="id", required = false) String id) {
        Category category = categoryService.findByUuid(id);
        if(category != null) {
            CategoryBean categoryBean = CategoryBean.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            ModelAndView modelAndView = getCreateOrEditModel(categoryBean, "edit");
            return modelAndView;
        }
        return new ModelAndView("redirect:/category/index");
    }


    @RequestMapping("/create")
    public ModelAndView create() {
        CategoryBean categoryBean = new CategoryBean();
        return getCreateOrEditModel(categoryBean, "create");
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("category")CategoryBean categoryBean, BindingResult result, Map model) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ModelAndView modelAndView = getCreateOrEditModel(categoryBean, "create");
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }

        long count = categoryService.getCountByName(categoryBean.getName());
        if(count == 0) {
            Category category = new Category();
            category.setName(categoryBean.getName());
            categoryService.create(category);
            ModelAndView modelAndView = new ModelAndView("redirect:/category/index");
            return modelAndView.addObject("success", String.format("Successfully create category %s", category.getName()));
        } else {
            ModelAndView modelAndView = getCreateOrEditModel(categoryBean, "create");
            modelAndView.addObject("error", "Duplicate category name, please use another name.");
            return modelAndView;
        }

    }

    @RequestMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("category")CategoryBean categoryBean, BindingResult result, Map model) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ModelAndView modelAndView = getCreateOrEditModel(categoryBean, "edit");
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/category/index");
        Category category = categoryService.findByUuid(categoryBean.getUuid());
        if(category != null) {
            long count = categoryService.getCountByName(categoryBean.getName());
            if( count != 0) {
                ModelAndView mode = getCreateOrEditModel(categoryBean, "edit");
                mode.addObject("error", "Duplicate category name, please use another name.");
                return mode;
            }
            category.setName(categoryBean.getName());
            categoryService.update(category);
            return modelAndView.addObject("success", String.format("Successfully updated category %s", category.getName()));
        }
        modelAndView.addObject("error", "Invalid edit category");
        return modelAndView;
    }

    private ModelAndView getCreateOrEditModel(CategoryBean categoryBean, String action) {
        ModelAndView modelAndView = new ModelAndView(String.format("category/%s", action));
        modelAndView.addObject("category", categoryBean );
        return modelAndView;
    }

}
