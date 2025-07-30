package org.cupinchacupons.backend.modules.user.controller;

import jakarta.validation.Valid;
import org.cupinchacupons.backend.modules.entity.UserEntity;
import org.cupinchacupons.backend.modules.user.repository.UserRepository;
import org.cupinchacupons.backend.modules.user.service.UserCreateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    private final UserCreateService userCreateService;

    public UserController(UserRepository userRepository, UserCreateService userCreateService) {
        this.userRepository = userRepository;
        this.userCreateService = userCreateService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserEntity userEntity) {
        try {
            var result = this.userCreateService.execute(userEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
