package org.launchcode.controllers;

import org.launchcode.models.Card;
import org.launchcode.models.Level;
import org.launchcode.models.Test;
import org.launchcode.models.data.CardDao;
import org.launchcode.models.data.LevelDao;
import org.launchcode.models.data.TestDao;
import org.launchcode.models.forms.EditTestCardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private TestDao testDao;

    @Autowired
    private LevelDao levelDao;

    @Autowired
    private CardDao cardDao;

    // Request path: /test
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("tests", testDao.findAll());
        model.addAttribute("title", "Fitness Tests");
        return "test/index";
    }

    // Request path: test/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddFitnessTestForm(Model model) {
        model.addAttribute("title", "Add Fitness Test");
        model.addAttribute(new Test());
        model.addAttribute("levels", levelDao.findAll());
        return "test/add";
    }

    // Request path: test/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddFitnessTestForm(@ModelAttribute @Valid Test newTest,
                                            Errors errors,
                                            @RequestParam int levelId,
                                            Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Fitness Test");
            model.addAttribute("levels", levelDao.findAll());
            return "test/add";
        }
        Level level = levelDao.findOne(levelId);
        newTest.setLevel(level);
        testDao.save(newTest);
        return "redirect:edit/" + newTest.getId();
    }

    // Request path: test/edit
    @RequestMapping(value = "edit/{testId}", method = RequestMethod.GET)
    public String displayEditFitnessTestForm(Model model, @PathVariable int testId) {
        Test editTest = testDao.findOne(testId);
//
//        model.addAttribute("test", editTest);
//        model.addAttribute("levels", levelDao.findAll());
//        model.addAttribute("cards", cardDao.findByLevel(editTest.getLevel()));
//
        EditTestCardForm form = new EditTestCardForm(editTest, levelDao.findAll(),
                cardDao.findByLevel(editTest.getLevel()));
        model.addAttribute("form", form);
        model.addAttribute("title", "Edit Fitness Test");
//
        return "test/edit";
    }

    // Request path: test/edit
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditFitnessTestForm(@ModelAttribute @Valid EditTestCardForm form,
                                             Errors errors,
                                             @RequestParam int[] cardIds,
                                             @RequestParam String testName,
                                             Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            model.addAttribute("title", "Edit Fitness Test");
            return "test/edit";
        }

        Test theTest = testDao.findOne(form.getTestId());
        theTest.removeItems();

        theTest.setName(testName);

        for (int cardId : cardIds) {
            if (cardId != -1) {
                Card theCard = cardDao.findOne(cardId);
                theTest = testDao.findOne(form.getTestId());
                theTest.addItem(theCard);
                testDao.save(theTest);
            }
        }

        return "redirect:";
    }
}
