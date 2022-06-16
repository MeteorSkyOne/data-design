package com.meteorsky.datadesign.Model;

import javax.persistence.*;

@Entity(name = "role")
public class Role {
    @Id    //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //自增主键
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public Role() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}