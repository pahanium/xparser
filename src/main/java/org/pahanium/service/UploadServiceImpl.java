package org.pahanium.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.pahanium.entity.*;
import org.pahanium.exception.SkipException;
import org.pahanium.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadRepository uploadRepository;

    @Override
    public List<Upload> getList(int page, int perPage) {
        return uploadRepository.getList(new PageRequest(page - 1, perPage));
    }

    @Override
    public List<Upload> getLast() {
        return getList(1, 5);
    }

    @Override
    public Long getCount() {
        return uploadRepository.count();
    }

    @Override
    @Transactional
    public void save(Upload upload) {
        uploadRepository.save(upload);
    }

    @Override
    public Upload findOne(Long id) {
        return uploadRepository.findOne(id);
    }

    @Override
    public void parse(File file, Parser parser) throws Exception {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        Upload upload = new Upload(file.getName(), parser);
        List<Field> fields = parser.getFields();
        if (fields.isEmpty()) {
            throw new Exception("Field list is empty");
        }
        // for (int i = 0; i < wb.getNumberOfSheets(); i++) {
        Sheet sheet = wb.getSheetAt(0);
        for (org.apache.poi.ss.usermodel.Row row : sheet) {
            org.pahanium.entity.Row newRow = new org.pahanium.entity.Row(row.getRowNum());
            try {
                for (Field field : fields) {
                    Cell cell = row.getCell(field.getColumn());
                    if (cell != null) {
                        String str = cell.toString();
                        for (Function function : field.getFunctions()) {
                            str = function.run(str);
                        }
                        newRow.addValue(new Value(field, str));
                    }
                }
                if (newRow.getValues().size() > 0) {
                    upload.addRow(newRow);
                    System.out.println(newRow);
                }
            } catch (SkipException e) {
                //Skip addRow if function throws
                System.out.println("Skip: " + newRow); //DEBUG
            }
        }

        upload.setRowsCount(upload.getRows().size());
        save(upload);
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
