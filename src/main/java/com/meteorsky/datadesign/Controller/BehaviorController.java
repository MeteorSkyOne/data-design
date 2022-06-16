package com.meteorsky.datadesign.Controller;

import com.meteorsky.datadesign.Model.Role;
import com.meteorsky.datadesign.Model.User;
import com.meteorsky.datadesign.Service.*;
import com.meteorsky.datadesign.Utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/behavior")
public class BehaviorController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private NewspaperService newspaperService;

    @Autowired
    private NewspaperClassService newspaperClassService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/query")
    public <T> JsonResult query(@RequestParam int type, @RequestParam T var, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.getUserByUsername(principal.getName());
        if(userService.hasRole(user.getUsername(), "ADMIN")) {
            if(var.equals("-1")){
                return JsonResult.success(orderService.queryAll());
            }
            switch (type) {
                case 1:
                    // User
                    return JsonResult.success(orderService.queryUser(Integer.parseInt((String) var)));
                case 2:
                    // Department
                    return JsonResult.success(orderService.queryDepartment(Integer.parseInt((String) var)));
                case 3:
                    // Newspaper
                    return JsonResult.success(orderService.queryNewspaper((String)var));
                case 4:
                    // NewspaperClass
                    return JsonResult.success(orderService.queryNewspaperClass((Integer.parseInt((String) var))));
                default:
                    return JsonResult.fail("type error");
            }
        }else{
            return JsonResult.success(orderService.queryUser(user.getId()));
        }
    }

    @GetMapping("/getAllTypes")
    public JsonResult getAllTypes(@RequestParam int type) {
        switch (type) {
            case 1:
                // User
                return JsonResult.success(userService.getAll());
            case 2:
                // Department
                return JsonResult.success(departmentService.getAll());
            case 3:
                // Newspaper
                return JsonResult.success(newspaperService.getAll());
            case 4:
                // NewspaperClass
                return JsonResult.success(newspaperClassService.getAll());
            default:
                return JsonResult.fail("type error");
        }
    }

    @GetMapping("/summary")
    public JsonResult summary(@RequestParam int type, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userService.getUserByUsername(principal.getName());
        if(userService.hasRole(user.getUsername(), "ADMIN")) {
            switch (type) {
                case 1:
                    // User
                    return JsonResult.success(orderService.summaryByUsers());
                case 2:
                    // Department
                    return JsonResult.success(orderService.summaryByDepartment());
                case 3:
                    // Newspaper
                    return JsonResult.success(orderService.summaryByNewspaper());
                case 4:
                    // NewspaperClass
                    return JsonResult.success(orderService.summaryByNewspaperClass());
                default:
                    return JsonResult.fail("type error");
            }
        }else{
            return JsonResult.success(orderService.summaryByUser(user.getUsername()));
        }

    }

}
