package com.lms.services.imageService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * Created by bhushan on 18/4/17.
 */
@Service
@Slf4j
public class CloudinaryImageServiceImpl implements ImageService{
    @Value("${cloud_name}")
    private String CLOUDNAME;
    @Value("${api_key}")
    private String APIKEY;
    @Value("${api_secret}")
    private String APISECRET;
    @Value("${resource_url}")
    private String RESOURCEURL;

    @Override
    public String uploadImage(MultipartFile sourceFile) throws Exception {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUDNAME,
                "api_key", APIKEY,
                "api_secret", APISECRET));
        try {
            Map<String, Object> cloudinaryURL = uploadToCloudinary(cloudinary, sourceFile);
           return String.format("%s.%s",cloudinaryURL.get("public_id"), cloudinaryURL.get("format"));
        } catch (Exception e) {
            log.error("Could not upload file to Cloundinary from MultipartFile exception occur" + e);
            throw e;
        }

    }

    public static Map<String, Object> uploadToCloudinary(Cloudinary cloudinary,MultipartFile sourceFile)throws IOException {

        Map<String, Object> cloudinaryUrl = null;
        Map params = new HashMap();
        params.put("filename", UUID.randomUUID().toString());

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> result = (Map<String, Object>)cloudinary.uploader().upload(sourceFile.getBytes(), params);
            cloudinaryUrl = result;
        } catch (IOException e) {
           throw e;
        }

        return cloudinaryUrl;
    }

    @Override
    public String resourceUrl(String fileName) {
        return String.format("%s%s",RESOURCEURL,fileName);
    }
}
