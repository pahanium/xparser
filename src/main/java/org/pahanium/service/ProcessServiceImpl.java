package org.pahanium.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.pahanium.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private UploadService uploadService;

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
        List<Field> fields = upload.getParser().getFields();
        if (fields.isEmpty()) {
            throw new Exception("Field list is empty");
        }
        // for (int i = 0; i < wb.getNumberOfSheets(); i++) {
        Sheet sheet = wb.getSheetAt(0);
        for (Row row : sheet) {
            org.pahanium.entity.Row newRow = new org.pahanium.entity.Row(row.getRowNum());
            upload.addRow(newRow);
            for (Field field : fields) {
                Cell cell = row.getCell(field.getColumn());
                if (cell != null) {
                    String str = row.getCell(field.getColumn()).toString();
                    newRow.addValue(new Value(field, str));
                }
            }
            System.out.println(newRow);
        }

        upload.setRowsCount(upload.getRows().size());
        uploadService.save(upload);
    }
}
