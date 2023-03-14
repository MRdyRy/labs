package com.rudy.ryanto.tesseract.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
public class FileUploadUtil {

    private static List<String> listAllowMimeType = List.of(Constants.MIMETYPE.JPEG, Constants.MIMETYPE.PNG);

    public static boolean upload(String uploadDir, String fileName, MultipartFile multipartFile) throws Exception{
        log.info("process upload : {}",fileName);
        Path path = Paths.get(uploadDir);

        boolean isSuccess = false;
        doCheckPath(path);
        String mimeType = Files.probeContentType(new File(fileName).toPath());
        isSuccess = doValidateMimeType(isSuccess, mimeType);
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
            File f = new File(uploadDir+"/"+fileName);
            isSuccess = doCheckSuccessUpload(isSuccess, f);
            log.info(isSuccess?"success upload file :) ":"failed to upload file :(");
        }catch(Exception e){
            isSuccess = false;
            throw new Exception("Error Upload file : ",e);
        }
        return isSuccess;
    }

    private static void doCheckPath(Path path) throws IOException {
        if(!Files.exists(path))
            Files.createDirectories(path);
    }

    private static boolean doValidateMimeType(boolean isSuccess, String mimeType) throws Exception {
        if(!listAllowMimeType.contains(mimeType)){
            isSuccess = false;
            throw new Exception("Format File Not Allowed!! "+ mimeType);
        }
        return isSuccess;
    }

    private static boolean doCheckSuccessUpload(boolean isSuccess, File f) {
        if(f.exists() && !f.isDirectory())
            isSuccess = true;
        return isSuccess;
    }
}