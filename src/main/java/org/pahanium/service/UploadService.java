package org.pahanium.service;

import org.pahanium.entity.Upload;

import java.util.List;

public interface UploadService {
    List<Upload> list();

    List<Upload> getLast();

    void save(Upload upload);

    Upload findOne(Long id);
}
