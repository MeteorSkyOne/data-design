package com.meteorsky.datadesign.Controller;


import com.meteorsky.datadesign.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping("/doRegister")
    public String doRegister(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password){
        request.getSession().removeAttribute("reg_result");
        int regResult = userService.register(username, password);
        request.getSession().setAttribute("reg_result", regResult);
        return "register";
    }
}
