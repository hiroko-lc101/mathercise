package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Level {

    /**
     * fields
     */

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=5, max=25)
    private String name;

    @OneToMany
    @JoinColumn(name = "level_id")
    private List<Card> cards = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "level_id")
    private List<Test> tests = new ArrayList<>();

    /**
     * constructors
     */

    public Level() { }

    public Level(String name) {
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
}