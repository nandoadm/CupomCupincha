package org.cupinchacupons.backend.modules.loja.repository;


import org.cupinchacupons.backend.modules.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LojaRepository extends JpaRepository<LojaEntity, UUID> {
    List<LojaEntity> findByNomeContainingIgnoreCase(String nome);
    Optional<LojaEntity> findByNome(String nome);
}
