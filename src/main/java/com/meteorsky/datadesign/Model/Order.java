package com.meteorsky.datadesign.Model;

import javax.persistence.*;

@Entity(name = "orders")
public class Order {
    @Id
    private String id;

    @Column
    private int number;

    @Column
    private int months;

    @Column
    private float price;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user")
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "newspaper")
    private Newspaper newspaper;

    public Order() { }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Newspaper getNewspaper() {
        return newspaper;
    }

    public void setNewspaper(Newspaper newspaper) {
        this.newspaper = newspaper;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
