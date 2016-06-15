package org.pahanium.controller;

import org.pahanium.entity.Upload;
import org.pahanium.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

@Controller
public class ExportController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export (@RequestParam Long id, HttpServletResponse response) {
        Upload upload = uploadService.findOne(id);
        File file = new File(upload.getFilename());
        response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file)); //TODO need gen new stream
            FileCopyUtils.copy(stream, response.getOutputStream());
            response.flushBuffer();
            stream.close();
        } catch (Exception e) {
            //FIXME log and return result
        }
    }
}
