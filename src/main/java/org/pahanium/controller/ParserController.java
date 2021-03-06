package org.pahanium.controller;

import org.pahanium.entity.Field;
import org.pahanium.entity.Function;
import org.pahanium.entity.Parser;
import org.pahanium.entity.enums.FunctionTypeEnum;
import org.pahanium.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Iterator;

@Controller
public class ParserController {
    @Autowired
    private ParserService parserService;

    @RequestMapping("/admin/parser-list")
    public ModelAndView index() {
        return new ModelAndView("admin/parser-list", "parsers", parserService.list());
    }

    @RequestMapping(value = "/admin/parser-add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("parser", new Parser());
        model.addAttribute("functionTypeEnum", FunctionTypeEnum.values());
        return "admin/parser-edit";
    }

    @RequestMapping(value = "/admin/parser-edit", method = RequestMethod.GET)
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("parser", parserService.findOne(id));
        model.addAttribute("functionTypeEnum", FunctionTypeEnum.values());

        return "admin/parser-edit";
    }

    // Manage dynamically added or removed Fields
    private void manageParser(Parser parser) {
        if (parser.getFields() != null) {
            for (Iterator<Field> i = parser.getFields().iterator(); i.hasNext(); ) {
                Field field = i.next();
                // If the remove flag is true, remove the employee from the list
                if (field.getRemove() == 1 || field.getTitle() == null) {
                    i.remove();
                } else {
                    for (Iterator<Function> j = field.getFunctions().iterator(); j.hasNext(); ) {
                        Function function = j.next();
                        if (function.getRemove() == 1 || function.getType() == null) {
                            j.remove();
                        }
                        else function.setField(field);
                    }
                    // Otherwise, perform the links
                    field.setParser(parser);
                }
            }
        }
    }

    @RequestMapping(value = "/admin/parser-edit", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("parser") Parser parser, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        // If we have errors, don't save
        if (bindingResult.hasErrors()) {
            // Put what they did in the model and send it back
            model.addAttribute(parser);
            model.addAttribute("message", "Error validate fields");
            return "admin/parser-edit";
        }

        manageParser(parser);
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
