package org.pahanium.controller;

import org.pahanium.entity.Parser;
import org.pahanium.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ParserController {
    @Autowired
    private ParserService parserService;

    @RequestMapping("/admin/parser-list")
    public ModelAndView index() {
        return new ModelAndView("parser-list", "parsers", parserService.list());
    }

    @RequestMapping("/admin/parser-add")
    public String add() {
        return "parser-edit";
    }

    @RequestMapping("/admin/parser-edit")
    public ModelAndView edit(@RequestParam Long id) {
        Parser parser = parserService.findOne(id);
        return new ModelAndView("parser-edit", "parser", parser);
    }

    @RequestMapping("/admin/parser-save")
    public String save(@RequestParam(required = false) Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String description, RedirectAttributes redirectAttributes) {
        Parser parser;
        if (id == null) {
            parser = new Parser();
        } else {
            parser = parserService.findOne(id);
        }

        parser.setName(name);
        parser.setDescription(description);
        parserService.save(parser);
        redirectAttributes.addFlashAttribute("message", "Saved successfully");
        return "redirect:/admin/parser-list";
    }

    @RequestMapping("/admin/parser-delete")
    public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        parserService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Deleted successfully");
        return "redirect:/admin/parser-list";
    }
}
