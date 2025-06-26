package org.backendlogin.frontend.modules.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserController {

    @GetMapping("/")
    public String login() {
        return "users/login";
    }

    @PostMapping("/signIn")
    public String signIn(String username, String password) {
        if (username.equals("admin") && password.equals("123")) {
            return "users/admin";
        }
        return "redirect:/login/";
    }

//    @GetMapping("/admin")
//    public String admin() {
//        return "users/admin";
//    }
}
