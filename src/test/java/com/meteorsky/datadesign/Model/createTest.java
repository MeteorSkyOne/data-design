package com.meteorsky.datadesign.Model;

import com.meteorsky.datadesign.Repository.NewspaperRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class createTest {
    @Autowired
    private NewspaperRepository newspaperRepository;


    @Test
    void createNewspaperClass() {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"backup/");
    }
}
