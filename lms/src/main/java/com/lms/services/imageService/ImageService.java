package com.lms.services.imageService;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by bhushan on 18/4/17.
 */
public interface ImageService {
    String uploadImage(MultipartFile sourceFile) throws Exception;
    String resourceUrl(String fileName);
}
