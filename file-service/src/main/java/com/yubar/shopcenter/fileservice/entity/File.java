package com.yubar.shopcenter.fileservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Yubar on 1/15/2017.
 */

@Entity
public class File implements Serializable {

    private String id;
    private String name;
    private String contentType;
    private Long size;
    private byte[] content;

    protected File() {
    }

    public File(String name, String contentType, Long size, byte[] content) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36)
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    public String getContentType() {
        return contentType;
    }

    private void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @NotNull
    @Min(1)
    public Long getSize() {
        return size;
    }

    private void setSize(Long size) {
        this.size = size;
    }

    @NotNull
    @Column(length = 1024 * 1024 * 100)
    public byte[] getContent() {
        return content;
    }

    private void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) &&
                Objects.equals(name, file.name) &&
                Objects.equals(contentType, file.contentType) &&
                Objects.equals(size, file.size) &&
                Arrays.equals(content, file.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType, size, content);
    }
}
