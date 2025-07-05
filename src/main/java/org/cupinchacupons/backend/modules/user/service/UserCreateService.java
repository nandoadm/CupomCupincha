package org.cupinchacupons.backend.modules.user.service;


import org.cupinchacupons.backend.modules.entity.UserEntity;
import org.cupinchacupons.backend.modules.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserCreateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity execute(UserEntity userEntity) {
        //Utiliza o JPA para fazer a busca no banco de dados
        this.userRepository.findByUsername(userEntity.getUsername()).ifPresent(user -> {
            //Retorna erro caso esteja presente
            throw new RuntimeException("Usuario já existe");
        });
        var password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);

        //Salva o usuario no banco de dados caso não esteja presente
        return userRepository.save(userEntity);
    }
}
