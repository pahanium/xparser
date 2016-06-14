package org.pahanium.service;

import org.apache.poi.ss.usermodel.*;
import org.pahanium.entity.Parser;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Override
    public void parse(File file, Parser parser) throws Exception {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        // for (int i = 0; i < wb.getNumberOfSheets(); i++) {
        Sheet sheet = wb.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println(System.lineSeparator());
        }
    }
}
