package org.pahanium.controller;

import org.pahanium.service.ParserService;
import org.pahanium.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {
    @Autowired
    private ParserService parserService;

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("uploads", uploadService.getLast());
        model.addAttribute("parsers", parserService.list());
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/help")
    public String help() {
        return "help";
    }

    @RequestMapping("/admin/settings")
    public String settings() {
        return "admin/settings";
    }
}
