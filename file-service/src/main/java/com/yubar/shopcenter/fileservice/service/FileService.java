package com.yubar.shopcenter.fileservice.service;

import com.yubar.shopcenter.fileservice.model.FileModel;
import com.yubar.shopcenter.fileservice.entity.File;
import com.yubar.shopcenter.fileservice.repository.FileRepository;
import com.yubar.shopcenter.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by yubar on 7/3/17.
 */

@Service
@Transactional
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileModel find(String id) {
        return fileRepository.findById(id)
                .map(FileModel::modelOf)
                .orElseThrow(ExceptionUtils::notFound);
    }

    public FileModel save(FileModel model) {
        File file = new File(model.getName(), model.getContentType(), model.getSize(), model.getContent());
        fileRepository.save(file);
        return FileModel.modelOf(file);
    }

}
