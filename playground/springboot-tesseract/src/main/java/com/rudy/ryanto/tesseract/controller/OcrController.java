package com.rudy.ryanto.tesseract.controller;

import com.rudy.ryanto.tesseract.service.OcrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/api/v1/ocr")
public class OcrController {
    @Autowired
    OcrService ocrService;

    @PostMapping("/upload")
    public ResponseEntity doUploadFile(@RequestParam("image") MultipartFile multipartFile) throws Exception{
        log.info("inside /api/v1/ocr/upload");
        String response = ocrService.upload(multipartFile);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}