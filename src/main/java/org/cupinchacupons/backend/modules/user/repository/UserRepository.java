package org.cupinchacupons.backend.modules.user.repository;


import org.cupinchacupons.backend.modules.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}
