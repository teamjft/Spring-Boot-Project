package com.lms.utils.helper;

import static com.lms.utils.constants.Constant.PAGE_SIZE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bhushan on 24/5/17.
 */
public class PaginationHelper {
    public static PageRequest getPageRequest(Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber =1;
        }
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        return request;
    }

    public static ModelAndView getModelAndView(String modelAndViewName, Page page, String beanName) {
        ModelAndView modelAndView = new ModelAndView(modelAndViewName);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        modelAndView.addObject(beanName,  page.getContent());
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        return modelAndView;
    }

}
