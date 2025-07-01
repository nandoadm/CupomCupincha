package org.cupinchacupons.frontend.modules.controllers;

import org.cupinchacupons.frontend.modules.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class UserFrontController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "users/login";
    }

    @PostMapping("/admin")
    public String signIn( RedirectAttributes redirectAttributes, String username, String password) {
        try {
            userService.login(username, password);

            return "users/admin";

        } catch (Exception e) {
            if(username.isEmpty() || password.isEmpty()){
                redirectAttributes.addFlashAttribute("user_error", "Preencha todos os campos");
                return "redirect:/login/";
            }
            redirectAttributes.addFlashAttribute("user_error", "Usuario/Senha incorretos");
            return "redirect:/login/";
        }
    }

    ;

    @GetMapping("/admin")
    public String admin() {
        return "users/admin";
    }
}
