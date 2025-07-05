package org.cupinchacupons.backend.modules.afiliado.repository;

import org.cupinchacupons.backend.modules.entity.AfiliadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AfiliadoRepository extends JpaRepository<AfiliadoEntity, UUID> {
    Optional<AfiliadoEntity> findByCnpj(String cnpj);
}
