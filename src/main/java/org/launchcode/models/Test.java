package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Test {

    /**
     * fields
     */

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    @ManyToOne
    private Level level;

    @OneToMany
    @JoinColumn(name = "test_id")
    private List<TestQuestion> testQuestions = new ArrayList<>();

    /**
     * constructors
     */

    public Test() { }

    public Test(String name) {
        this.name = name;
    }

    /**
     * getters & setters
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}