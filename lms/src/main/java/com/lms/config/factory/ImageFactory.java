package com.lms.config.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.services.imageService.CloudinaryImageServiceImpl;
import com.lms.services.imageService.ImageService;
import com.lms.services.imageService.LocalStorageServiceImpl;
import com.lms.utils.enums.SaveImageServiceType;

/**
 * Created by bhushan on 18/4/17.
 */
@Component
public class ImageFactory {
    @Autowired
    private CloudinaryImageServiceImpl cloudinaryImageService;
    @Autowired
    private LocalStorageServiceImpl localStorageServiceImpl;

    public ImageService getSendContentService(SaveImageServiceType saveImageServiceType) {
        switch (saveImageServiceType) {
            case CLOUDINARY:
                return cloudinaryImageService;
            default:
                return localStorageServiceImpl;
        }
    }
}
