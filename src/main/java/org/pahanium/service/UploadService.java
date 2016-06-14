package org.pahanium.service;

import org.pahanium.entity.Upload;

import java.util.List;

public interface UploadService {
    List<Upload> list();

    void save(Upload upload);
}
