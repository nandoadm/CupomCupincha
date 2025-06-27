package org.cupinchacupons.frontend.modules.controllers;

import org.cupinchacupons.backend.modules.dto.AuthUserRequestDTO;
import org.cupinchacupons.backend.modules.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class UserFrontController {

    @Autowired
    private AuthUserService authUserService;

    @GetMapping("/")
    public String login() {
        return "users/login";
    }

    @PostMapping("/admin")
    public String signIn(RedirectAttributes redirectAttributes, @RequestParam String username, @RequestParam String password) {
        try {
            AuthUserRequestDTO authRequest = new AuthUserRequestDTO(username, password);
            var result = authUserService.execute(authRequest);

            return "users/admin";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("user_error", "Usuario/Senha incorretos");
            return "redirect:/login/";
        }
    };
    @GetMapping("/admin")
    public String admin() {
        return "users/admin";
    }
}
