package com.rudy.ryanto.tesseract.service;


import com.rudy.ryanto.tesseract.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Service
public class OcrService {

    @Value("${upload.directory}")
    private String uploadDirPath;

    @Autowired
    Tesseract tesseract;

    public String upload(MultipartFile multipartFile){
        log.info("inside ocr service ");
        String resultOCR = "";
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = uploadDirPath+"/upload";
        try {
            boolean isSuccess = FileUploadUtil.upload(uploadDir,fileName,multipartFile);
            if(isSuccess){
                log.info("do ocr!");
                String text = tesseract.doOCR(new File(uploadDir+"/"+fileName));
                resultOCR = text;
                log.info("result : {}", resultOCR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultOCR;
    }
}