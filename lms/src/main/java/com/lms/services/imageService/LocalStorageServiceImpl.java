package com.lms.services.imageService;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by bhushan on 19/4/17.
 */
@Service
public class LocalStorageServiceImpl implements ImageService {

    @Value("${localstroge.path}")
    private String filePath;
    @Override
    public String uploadImage(MultipartFile sourceFile) throws Exception {
        byte[] bytes = sourceFile.getBytes();
        String originalName = sourceFile.getOriginalFilename();

        File file=new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName =String.format("%s%s", UUID.randomUUID().toString(), originalName);
        File temp=new File(file,fileName);
        FileOutputStream fos= new FileOutputStream(temp);
        fos.write(bytes);
        fos.close();
        return fileName;
    }

    @Override
    public String resourceUrl(String fileName) {
        return String.format("%s%s",filePath,fileName);
    }
}
