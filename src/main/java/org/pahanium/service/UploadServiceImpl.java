package org.pahanium.service;

import org.pahanium.entity.Upload;
import org.pahanium.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadRepository uploadRepository;

    @Override
    public List<Upload> list() {
        return uploadRepository.findAll();
    }

    @Override
    public void save(Upload upload) {
        uploadRepository.save(upload);
    }
}
