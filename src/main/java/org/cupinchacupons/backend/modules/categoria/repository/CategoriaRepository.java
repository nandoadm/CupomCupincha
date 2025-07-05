package org.cupinchacupons.backend.modules.categoria.repository;

import org.cupinchacupons.backend.modules.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID> {
}
