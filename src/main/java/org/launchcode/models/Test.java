package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @ManyToMany
    private List<Card> cards;

//    private int order;

    /**
     * constructors
     */

    public Test() { }

    public Test(String name) {
//    public Test(String name, int order) {
        this.name = name;
//        this.order = order;
    }

    /**
     * methods
     */

    public void addItem(Card item) {
        cards.add(item);
    }

    public void removeItems() {
        cards.clear();
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

//    public int getOrder() {
//        return order;
//    }

//    public void setOrder(int order) {
//        this.order = order;
//    }

    public List<Card> getCards() {
        return cards;
    }

}
