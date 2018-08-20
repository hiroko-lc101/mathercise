package org.launchcode.controllers;

import org.launchcode.models.Card;
import org.launchcode.models.Level;
import org.launchcode.models.data.CardDao;
import org.launchcode.models.data.LevelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardDao cardDao;

    @Autowired
    private LevelDao levelDao;

    // Request path: card
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cards", cardDao.findAllByOrderByLevelAsc());
        model.addAttribute("title", "Flash Cards");
        return "card/index";
    }

    // Request path: card/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddFlashCardForm(Model model) {
        model.addAttribute("title", "Add Flash Card");
        model.addAttribute(new Card());
        model.addAttribute("levels", levelDao.findAll());
        return "card/add";
    }

    // Request path: card/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddFlashCardForm(@ModelAttribute @Valid Card newCard,
                                          Errors errors,
                                          @RequestParam int levelId,
                                          Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Flash Card");
            model.addAttribute("levels", levelDao.findAll());
            return "card/add";
        }
        Level level = levelDao.findOne(levelId);
        newCard.setLevel(level);
        cardDao.save(newCard);
        return "redirect:";
    }

    // Request path: card/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveFlashCardForm(Model model){
        model.addAttribute("cards", cardDao.findAllByOrderByLevelAsc());
        model.addAttribute("title", "Remove Flash Cards");
        return "card/remove";
    }

    // Request path: card/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveFlashCardForm(@RequestParam int[] cardIds) {
        for (int cardId : cardIds) {
            cardDao.delete(cardId);
        }
        return "redirect:";
    }

    // Request path: card/edit
    @RequestMapping(value = "edit/{cardId}", method = RequestMethod.GET)
    public String displayEditFlashCardForm(Model model, @PathVariable int cardId) {
        model.addAttribute("card", cardDao.findOne(cardId));
        model.addAttribute("levels", levelDao.findAll());
        model.addAttribute("title", "Edit Flash Card");
        return "card/edit";
    }

    // Request path: card/edit
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditFlashCardForm(int cardId, String question, String answer, int levelId) {
        Card editCard = cardDao.findOne(cardId);
        editCard.setQuestion(question);
        editCard.setAnswer(answer);
        Level level = levelDao.findOne(levelId);
        editCard.setLevel(level);
        cardDao.save(editCard);
        return "redirect:";
    }
}

