package org.launchcode.controllers;

import org.launchcode.models.data.TestDao;
import org.launchcode.models.data.TestQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("assessment")
public class AssessmentController {

    @Autowired
    private TestDao testDao;

    @Autowired
    private TestQuestionDao testQuestionDao;

    // Request path: assessment
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("tests", testDao.findAllByOrderByLevelAsc());
        model.addAttribute("title", "Assessments");
        return "assessment/index";
    }

    // Request path: assessment
    @RequestMapping(value = "assess/{testId}", method = RequestMethod.GET)
    public String FitnessTest(Model model, @PathVariable int testId) {
        model.addAttribute("testQuestions", testQuestionDao.findByTestIdOrderByQuestionNumberAsc(testId));
        model.addAttribute("title", testDao.findOne(testId).getName());
        return "assessment/assess";
    }
}