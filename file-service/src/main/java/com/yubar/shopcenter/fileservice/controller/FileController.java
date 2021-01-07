package com.yubar.shopcenter.fileservice.controller;

import com.yubar.shopcenter.fileservice.model.FileModel;
import com.yubar.shopcenter.fileservice.service.FileService;
import com.yubar.shopcenter.security.IsUser;
import com.yubar.shopcenter.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    @IsUser
    public FileModel uploadFile(MultipartHttpServletRequest request) throws IOException {

        MultipartFile file = request.getFile("file");
        if (file == null)
            throw ExceptionUtils.badRequest();

        FileModel model =
                new FileModel(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());

        model = fileService.save(model);
        model.setContent(null);
        return model;

    }

    @GetMapping("/{id}")
    public void getFile(@PathVariable String id, HttpServletResponse response) throws IOException {

        FileModel file = fileService.find(id);
        response.setContentType(file.getContentType());
        response.setContentLengthLong(file.getSize());
        response.setHeader("Content-Disposition", "inline" + "; filename=" + file.getName());
        response.getOutputStream().write(file.getContent());

    }
}
