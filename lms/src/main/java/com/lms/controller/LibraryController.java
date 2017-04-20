package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.Library;
import com.lms.services.library.LibraryService;
import com.lms.utils.beans.LibraryBean;
import com.lms.utils.converter.SaveImageServiceTypeConverter;
import com.lms.utils.enums.SaveImageServiceType;

/**
 * Created by bhushan on 19/4/17.
 */

@Controller
@RequestMapping(value = "/library")
public class LibraryController {
    @Value("${localstroge.path}")
    private String LOCALSTROGEPATH;
    @Value("${resource_url}")
    private String CLOUDINARYSTROGEPATH;
    @Autowired
    private LibraryService libraryService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SaveImageServiceType.class, new SaveImageServiceTypeConverter());
    }

    @RequestMapping("/imageconfiguration")
    public ModelAndView imageConfiguration() {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        ModelAndView modelAndView = new ModelAndView("library/imageconfiguration");
        LibraryBean libraryBean = new LibraryBean();
        libraryBean.setSaveImageServiceType(library.getSaveImageServiceType());
        libraryBean.setUuid(library.getUuid());
        modelAndView.addObject("local", LOCALSTROGEPATH);
        modelAndView.addObject("cloudinary", CLOUDINARYSTROGEPATH);
        modelAndView.addObject("currentStorageType", library.getSaveImageServiceType());
        modelAndView.addObject("storageType", SaveImageServiceType.values());
        return modelAndView;
    }

    @RequestMapping("/updatestrogetype")
    public ModelAndView updateStrogeType(@RequestParam(value = "saveImageServiceType") SaveImageServiceType saveImageServiceType, Object command) {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        library.setSaveImageServiceType(saveImageServiceType);
        libraryService.update(library);
        return new ModelAndView("redirect:/library/imageconfiguration");
    }

}
