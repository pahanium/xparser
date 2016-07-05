package org.pahanium.controller;

import org.pahanium.entity.Parser;
import org.pahanium.service.ParserService;
import org.pahanium.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class UploadController {
    @Autowired
    private ParserService parserService;

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam Long id) {
        Parser parser = parserService.findOne(id);
        return new ModelAndView("upload", "parser", parser);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String submit(@RequestParam Long id, @RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
        Parser parser = parserService.findOne(id);

        if (!multipartFile.isEmpty()) {
            try {
                String fileName = multipartFile.getOriginalFilename();
                File file = new File(fileName);

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(file));
                FileCopyUtils.copy(multipartFile.getInputStream(), stream);
                stream.close();

                uploadService.parse(file, parser);

                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + fileName + "!");
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageFail",
                        "You failed to upload => " + e.getMessage());
            }
        }
        else {
            redirectAttributes.addFlashAttribute("messageFail",
                    "You failed to upload because the multipartFile was empty");
        }

        return "redirect:/upload?id=" + id;
    }

    @RequestMapping("/upload-list")
    public ModelAndView index() {
        return new ModelAndView("upload-list", "uploads", uploadService.list());
    }
}
