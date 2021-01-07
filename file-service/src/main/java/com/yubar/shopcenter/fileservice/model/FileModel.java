package com.yubar.shopcenter.fileservice.model;


import com.yubar.shopcenter.fileservice.entity.File;
import org.springframework.beans.BeanUtils;

/**
 * Created by Yubar on 1/15/2017.
 */
public class FileModel {

    private String id;
    private String name;
    private String contentType;
    private Long size;
    private byte[] content;

    public FileModel() {
    }

    public FileModel(String name, String contentType, long size, byte[] content) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public static FileModel modelOf(File file) {
        FileModel fileModel = new FileModel();
        BeanUtils.copyProperties(file, fileModel);
        return fileModel;
    }

}
