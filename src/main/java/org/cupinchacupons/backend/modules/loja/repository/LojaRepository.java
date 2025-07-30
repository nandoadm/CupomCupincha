package org.cupinchacupons.backend.modules.loja.repository;


import org.cupinchacupons.backend.modules.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LojaRepository extends JpaRepository<LojaEntity, UUID> {
    Optional<LojaEntity> findByNome(String nome);
}
