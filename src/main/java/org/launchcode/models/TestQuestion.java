package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class TestQuestion {

    /**
     * fields
     */

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Test test;

    @ManyToOne
    private Card card;

    @NotNull
    private int questionNumber;

    /**
     * constructors
     */

    public TestQuestion() { }

    public TestQuestion(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    /**
     * getters & setters
     */

    public int getId() {
        return id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}
