package com.meteorsky.datadesign.Controller;

import com.meteorsky.datadesign.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request){
        request.getSession().removeAttribute("reg_result");
        return "register";
    }

    @RequestMapping("/layout")
    public String layout(){
        return "layout";
    }


    @RequestMapping("/entry")
    public String entry(){
        return "entry";
    }

    @RequestMapping("/entry_news")
    public String entry_news(){
        return "entry_news";
    }

    @RequestMapping("/subscribe")
    public String subscribe(){
        return "subscribe";
    }

    @RequestMapping("/query")
    public String query(){
        return "query";
    }

    @RequestMapping("/summary")
    public String summary(){
        return "summary";
    }

    @RequestMapping("/backup")
    public String backup(){
        return "backup";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

}
