package org.launchcode.controllers;

import org.launchcode.models.Level;
import org.launchcode.models.data.LevelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("level")
public class LevelController {

    @Autowired
    private LevelDao levelDao;

    // Request path: level
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("levels", levelDao.findAll());
        model.addAttribute("title", "Levels");
        return "level/index";
    }

    // Request path: level/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddLevelForm(Model model) {
        model.addAttribute("title", "Add Level");
        model.addAttribute(new Level());
        return "level/add";
    }

    // Request path: level/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddLevelForm(@ModelAttribute @Valid Level newLevel,
                                          Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Level");
            return "level/add";
        }
        levelDao.save(newLevel);
        return "redirect:";
    }

    // Request path: level/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveLevelForm(Model model){
        model.addAttribute("levels", levelDao.findAll());
        model.addAttribute("title", "Remove Levels");
        return "level/remove";
    }

    // Request path: level/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveLevelForm(@RequestParam int[] levelIds) {
        for (int levelId : levelIds) {
            levelDao.delete(levelId);
        }
        return "redirect:";
    }

    // Request path: level/edit
    @RequestMapping(value = "edit/{levelId}", method = RequestMethod.GET)
    public String displayEditLevelForm(Model model, @PathVariable int levelId) {
        model.addAttribute("level", levelDao.findOne(levelId));
        model.addAttribute("title", "Edit Level");
        return "level/edit";
    }

    // Request path: level/edit
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditLevelForm(int levelId, String name) {
        Level editLevel = levelDao.findOne(levelId);
        editLevel.setName(name);
        levelDao.save(editLevel);
        return "redirect:";
    }
}