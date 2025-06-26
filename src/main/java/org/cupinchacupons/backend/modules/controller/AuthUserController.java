package org.cupinchacupons.backend.modules.controller;

import org.cupinchacupons.backend.modules.dto.AuthUserRequestDTO;
import org.cupinchacupons.backend.modules.service.AuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthUserRequestDTO authUserRequestDTO) {
        try {
            var result = this.authUserService.execute(authUserRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
