package com.meteorsky.datadesign.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class NewspaperClass {
    @Id
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "newspaperClass")
    @JsonIgnore
    private List<Newspaper> newspapers;

    public NewspaperClass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public NewspaperClass() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Newspaper> getNewspapers() {
        return newspapers;
    }

    public void setNewspapers(List<Newspaper> newspapers) {
        this.newspapers = newspapers;
    }
}
