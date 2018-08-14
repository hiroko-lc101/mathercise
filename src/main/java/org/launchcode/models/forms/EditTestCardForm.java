package org.launchcode.models.forms;

import org.launchcode.models.Card;
import org.launchcode.models.Level;
import org.launchcode.models.Test;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditTestCardForm {

    /**
     * fields
     */

    private Test test;

    private Iterable<Level> levels;

    private Iterable<Card> cards;

    @NotNull
    private int testId;

    @NotNull
    private int levelId;

    @NotNull
    private int cardId;

    /**
     * constructors
     */

    public EditTestCardForm() { }

    public EditTestCardForm(Test test, Iterable<Level> levels, Iterable<Card> cards) {
        this.test = test;
        this.levels = levels;
        this.cards = cards;
    }

    /**
     * accessors
     */

    public Test getTest() {
        return test;
    }

    public Iterable<Level> getLevels() {
        return levels;
    }

    public Iterable<Card> getCards() {
        return cards;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
