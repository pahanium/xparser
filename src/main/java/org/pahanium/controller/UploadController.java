package org.pahanium.controller;

import org.pahanium.entity.Parser;
import org.pahanium.entity.Upload;
import org.pahanium.service.ParserService;
import org.pahanium.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.List;

@Controller
public class UploadController {
    public static int PER_PAGE = 10;

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
    public String index(@RequestParam(name="page", required = false, defaultValue = "1") int page, Model model) {
        List<Upload> uploads = uploadService.getList(page, PER_PAGE);

        int current = page;
        int total = (int) (uploadService.getCount() / PER_PAGE) + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, total);

        model.addAttribute("uploads", uploads);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("totalIndex", total);
        model.addAttribute("currentIndex", current);

        return "upload-list";
    }
}
