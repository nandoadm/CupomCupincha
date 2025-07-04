package org.cupinchacupons.frontend.modules.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminFrontController {

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "users/admin";
    }

    @GetMapping("/{secao}")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(
            @PathVariable(name = "secao", required = false) String secao,
            Model model) {

        model.addAttribute("secao", secao);
        return "users/admin";
    }
}
