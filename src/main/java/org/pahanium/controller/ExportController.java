package org.pahanium.controller;

import org.pahanium.entity.Upload;
import org.pahanium.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class ExportController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Long id, HttpServletResponse response) {
        Upload upload = uploadService.findOne(id);

        String filename = upload.getFilename();
        filename = filename.substring(0, filename.lastIndexOf('.')) + ".csv";
        try {
            response.setContentType("text/csv; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(filename, "UTF-8") + "\"");
            uploadService.export(upload, response.getWriter());
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
