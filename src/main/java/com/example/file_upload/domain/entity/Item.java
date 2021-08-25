package com.example.file_upload.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;


    public Item(String itemName, UploadFile attachFile, List<UploadFile> imageFiles) {
        this.itemName = itemName;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }

    public static Item of(String itemName, UploadFile attachFile, List<UploadFile> imageFiles) {
        return new Item(itemName, attachFile, imageFiles);
    }
}
