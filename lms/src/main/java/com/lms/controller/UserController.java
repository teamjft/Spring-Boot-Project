package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.dao.LibraryDao;
import com.lms.models.Library;
import com.lms.services.library.LibraryService;
import com.lms.utils.beans.DataCount;

/**
 * Created by bhushan on 11/4/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {
@Autowired
private LibraryService libraryService;
    @RequestMapping("/home")
    public ModelAndView home() {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        ModelAndView modelAndView = new ModelAndView("user/home");
        DataCount dataCount = libraryService.basicCountInfoOfLibrary(library.getUuid());
        modelAndView.addObject("dataCount", dataCount);
        System.out.println("Hii..................................................");
        return modelAndView;
    }


}
