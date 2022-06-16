package com.meteorsky.datadesign;

import com.meteorsky.datadesign.Model.Newspaper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class DataDesignApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }

    @Test
    void createNewspaperClass() {
        Newspaper newspaper = new Newspaper();
        newspaper.setName("新聞");
    }

}
