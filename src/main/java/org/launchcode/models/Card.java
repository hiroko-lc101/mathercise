package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Card {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=5)
    private String question;

    @NotNull
    @Size(min=1, max=2)
    private String answer;

    @ManyToOne
    private Level level;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Card() { }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
