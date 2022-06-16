package com.meteorsky.datadesign.Controller;

import com.meteorsky.datadesign.Model.User;
import com.meteorsky.datadesign.Service.UserService;
import com.meteorsky.datadesign.Utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public JsonResult getAll() {
        return JsonResult.success(userService.getAll());
    }

    @GetMapping("/get/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult delete(@RequestParam String username) {
        return JsonResult.success(userService.deleteUser(username));
    }

    @PostMapping("/update")
    public JsonResult update(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String idcard,
                             @RequestParam String department,
                             @RequestParam String phone,
                             @RequestParam String address,
                             @RequestParam String email,
                             @RequestParam String realname,
                             HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String user = principal.getName();
        if(userService.hasRole(user, "ADMIN")) {
            if(username.equals("")) {
                return JsonResult.success(userService.updateUser(user,password,idcard,department,phone,address,email,realname));
            }else{
                return JsonResult.success(userService.updateUser(username,password,idcard,department,phone,address,email,realname));
            }
        } else {
            return JsonResult.success(userService.updateUser(user,password,idcard,department,phone,address,email,realname));
        }

    }

    @PostMapping("/promote")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult promote(@RequestParam String username) {
        return JsonResult.success(userService.promoteUser(username));
    }

    @GetMapping("/getMyInfo")
    public JsonResult getMyInfo(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return JsonResult.success(userService.getUserByUsername(username));
    }

}
