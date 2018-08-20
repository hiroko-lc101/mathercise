package org.launchcode.controllers;

import org.launchcode.models.Card;
import org.launchcode.models.Level;
import org.launchcode.models.Test;
import org.launchcode.models.TestQuestion;
import org.launchcode.models.data.CardDao;
import org.launchcode.models.data.LevelDao;
import org.launchcode.models.data.TestDao;
import org.launchcode.models.data.TestQuestionDao;
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
    private TestQuestionDao testQuestionDao;

    @Autowired
    private CardDao cardDao;

    // Request path: /test
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("tests", testDao.findAllByOrderByLevelAsc());
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

    // Request path: test/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveFitnessTestForm(Model model){
        model.addAttribute("tests", testDao.findAllByOrderByLevelAsc());
        model.addAttribute("title", "Remove Tests");
        return "test/remove";
    }

    // Request path: test/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveFitnessTestForm(@RequestParam int[] testIds) {
        for (int testId : testIds) {
            testDao.delete(testId);
        }
        return "redirect:";
    }

    // Request path: test/edit
    @RequestMapping(value = "edit/{testId}", method = RequestMethod.GET)
    public String displayEditFitnessTestForm(Model model, @PathVariable int testId) {
        Test editTest = testDao.findOne(testId);
        model.addAttribute("test", editTest);
        model.addAttribute("levels", levelDao.findAll());
        model.addAttribute("testQuestions", testQuestionDao.findByTestIdOrderByQuestionNumberAsc(testId));
        model.addAttribute("cards", cardDao.findByLevel(editTest.getLevel()));
        model.addAttribute("title", "Edit Fitness Test");
        return "test/edit";
    }

    //Request path: test/edit
    @RequestMapping(value = "edit", params = "addTestQuestion", method = RequestMethod.POST)
    public String addTestQuestion(Model model, int testId, int[] cardIds) {
        List<TestQuestion> testQuestions = testQuestionDao.findByTestId(testId);
        int questionNumber = testQuestions.size();
        TestQuestion newTestQuestion = new TestQuestion();
        Test test = testDao.findOne(testId);
        newTestQuestion.setTest(test);
        newTestQuestion.setQuestionNumber(questionNumber + 1);
        testQuestionDao.save(newTestQuestion);

        model.addAttribute("test", test);
        model.addAttribute("levels", levelDao.findAll());
        model.addAttribute("testQuestions", testQuestionDao.findByTestId(testId));
        model.addAttribute("cards", cardDao.findByLevel(test.getLevel()));
        model.addAttribute("title", "Edit Fitness Test");
        return "test/edit";
    }

    //Request path: test/edit
    @RequestMapping(value = "edit", params = "updateTestQuestion", method = RequestMethod.POST)
    public String updateTestQuestion(int testId, String name, int[] cardIds) {
        // update test name
        Test editTest = testDao.findOne(testId);
        if (!editTest.getName().equals(name)) {
            editTest.setName(name);
            testDao.save(editTest);
        }

        // update test question(s)
        List<TestQuestion> testQuestions = testQuestionDao.findByTestIdOrderByQuestionNumberAsc(testId);
        int questionNumber = 1;
        for (int i = 0; i < testQuestions.size(); i++) {
            if (cardIds[i] != -2) {
                // brand-new question entry or existing question entry with change
                if (testQuestions.get(i).getCard() == null ||
                        cardIds[i] != testQuestions.get(i).getCard().getId()) {
                    if (cardIds[i] == -1) {
                        // remove existing question entry
                        int testQuestionId = testQuestions.get(i).getId();
                        testQuestionDao.delete(testQuestionId);
                        questionNumber--;
                    } else {
                        // update existing record
                        int testQuestionId = testQuestions.get(i).getId();
                        TestQuestion editTestQuestion = testQuestionDao.findOne(testQuestionId);
                        Card card = cardDao.findOne(cardIds[i]);
                        editTestQuestion.setCard(card);
                        editTestQuestion.setQuestionNumber(questionNumber);
                        testQuestionDao.save(editTestQuestion);
                    }
                }
                // existing question entry with no change
                if (testQuestions.get(i).getCard() != null &&
                        cardIds[i] == testQuestions.get(i).getCard().getId()) {
                    int testQuestionId = testQuestions.get(i).getId();
                    TestQuestion editTestQuestion = testQuestionDao.findOne(testQuestionId);
                    // check and update question number if needed
                    if (editTestQuestion.getQuestionNumber() != questionNumber) {
                        editTestQuestion.setQuestionNumber(questionNumber);
                        testQuestionDao.save(editTestQuestion);
                    }
                }
                questionNumber++;
            } else {
                // remove unspecified question entry
                int testQuestionId = testQuestions.get(i).getId();
                testQuestionDao.delete(testQuestionId);
            }
        }
        return "redirect:";
    }
}