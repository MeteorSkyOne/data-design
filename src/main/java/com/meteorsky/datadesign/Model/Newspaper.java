package com.meteorsky.datadesign.Model;

import javax.persistence.*;

@Entity
public class Newspaper {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String publisher;
    @Column
    private String publishBetween;
    @Column
    private float price;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "class")
    private NewspaperClass newspaperClass;

    public NewspaperClass getNewspaperClass() {
        return newspaperClass;
    }

    public void setNewspaperClass(NewspaperClass newspaperClass) {
        this.newspaperClass = newspaperClass;
    }

    public Newspaper(String id, String name, String publisher, String publishBetween, float price, String content, NewspaperClass newspaperClass) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.publishBetween = publishBetween;
        this.price = price;
        this.content = content;
        this.newspaperClass = newspaperClass;
    }

    public Newspaper() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishBetween() {
        return publishBetween;
    }

    public void setPublishBetween(String publishBetween) {
        this.publishBetween = publishBetween;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
