package org.cupinchacupons.frontend.modules.home.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeFrontController {

    @GetMapping("/")
    public String exibirHome(){
        return "users/home";
    }
}
