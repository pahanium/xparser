package org.pahanium.service;

import org.pahanium.entity.Parser;
import org.pahanium.entity.Upload;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public interface UploadService {
    List<Upload> list();

    List<Upload> getLast();

    void save(Upload upload);

    Upload findOne(Long id);

    void parse(File file, Parser parser) throws Exception;

    void export(Upload upload, PrintWriter outputStream);
}
