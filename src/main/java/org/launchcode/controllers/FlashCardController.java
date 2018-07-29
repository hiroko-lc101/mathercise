package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "card")
public class FlashCardController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Flash Cards");
        return "card/index";
    }

}

