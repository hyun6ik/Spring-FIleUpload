package com.example.file_upload.domain.file;

import com.example.file_upload.domain.entity.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        final String originalFilename = multipartFile.getOriginalFilename();
        final String storeFileName = createStoreFileName(multipartFile);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return UploadFile.of(originalFilename, storeFileName);
    }


    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    private String createStoreFileName(MultipartFile multipartFile) {
        final String uuid = UUID.randomUUID().toString();
        final String ext = extractExt(multipartFile);
        return uuid + "." + ext;
    }

    private String extractExt(MultipartFile multipartFile) {
        final String originalFilename = multipartFile.getOriginalFilename();
        final int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
