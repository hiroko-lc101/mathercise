package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Card {

    /**
     * fields
     */

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

    @ManyToMany(mappedBy = "cards")
    private List<Test> tests;

    /**
     * constructors
     */

    public Card() { }

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * getters & setters
     */

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
