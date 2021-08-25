package com.example.file_upload.domain.entity;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public static UploadFile of(String uploadFileName, String storeFileName) {
        return new UploadFile(uploadFileName, storeFileName);
    }
}
