package org.launchcode.controllers;

import org.launchcode.models.Card;
import org.launchcode.models.Test;
import org.launchcode.models.TestQuestion;
import org.launchcode.models.data.CardDao;
import org.launchcode.models.data.LevelDao;
import org.launchcode.models.data.TestDao;
import org.launchcode.models.data.TestQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "test")
public class TestQuestionController {

    @Autowired
    private TestDao testDao;

    @Autowired
    private LevelDao levelDao;

    @Autowired
    private TestQuestionDao testQuestionDao;

    @Autowired
    private CardDao cardDao;

}