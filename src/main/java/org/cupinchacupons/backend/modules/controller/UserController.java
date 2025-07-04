package org.cupinchacupons.backend.modules.controller;

import jakarta.validation.Valid;
import org.cupinchacupons.backend.modules.Entity.UserEntity;
import org.cupinchacupons.backend.modules.repository.UserRepository;
import org.cupinchacupons.backend.modules.service.UserCreateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create-user")
public class UserController {

    private final UserRepository userRepository;

    private final UserCreateService userCreateService;

    public UserController(UserRepository userRepository, UserCreateService userCreateService) {
        this.userRepository = userRepository;
        this.userCreateService = userCreateService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserEntity userEntity) {
        try {
            var result = this.userCreateService.execute(userEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
