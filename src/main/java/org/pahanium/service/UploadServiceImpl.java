package org.pahanium.service;

import org.pahanium.entity.Field;
import org.pahanium.entity.Row;
import org.pahanium.entity.Upload;
import org.pahanium.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Collections;
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
    public List<Upload> getLast() {
        return uploadRepository.getLast();
    }

    @Override
    public void save(Upload upload) {
        uploadRepository.save(upload);
    }

    @Override
    public Upload findOne(Long id) {
        return uploadRepository.findOne(id);
    }

    @Override
    public void export(Upload upload, PrintWriter outputStream) {
        List<Field> fields = upload.getParser().getFields();
        Collections.sort(fields);

        boolean first = true;
        for (Field field : fields) {
            if (!first) {
                outputStream.print(",");
            } else {
                first = false;
            }
            outputStream.print(field.getTitle());
        }
        outputStream.println();

        for (Row row : upload.getRows()) {
            first = true;
            for (Field field : fields) {
                if (!first) {
                    outputStream.print(",");
                } else {
                    first = false;
                }
                outputStream.print("\"");
                outputStream.print(row.getValueByField(field));
                outputStream.print("\"");
            }
            outputStream.println();
        }
    }
}
