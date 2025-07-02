package org.cupinchacupons.frontend.modules.controllers;

import jakarta.servlet.http.HttpSession;
import org.cupinchacupons.frontend.modules.usecase.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class UserFrontController {

    private final UserService userService;

    public UserFrontController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String login() {
        return "users/login";
    }

    @PostMapping("/do-login")
    public String signIn(RedirectAttributes redirectAttributes, HttpSession session, String username, String password) {
        try {

            var token = userService.login(username, password);
            var grants = token.getRole().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                    .toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);
            auth.setDetails((token));

            SecurityContextHolder.getContext().setAuthentication(auth);
            auth.getAuthorities().forEach(authority -> System.out.println("Permissão: " + authority.getAuthority()));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            session.setAttribute("token", securityContext);

            return "redirect:/admin/";
        } catch (Exception e) {
            if (username.isEmpty() || password.isEmpty()) {
                redirectAttributes.addFlashAttribute("user_error", "Preencha todos os campos");
                return "redirect:/login/";
            }
            redirectAttributes.addFlashAttribute("user_error", "Usuario/Senha incorretos");
            return "redirect:/login/";
        }
    }

    ;


}
