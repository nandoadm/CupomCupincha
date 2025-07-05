package org.cupinchacupons.backend.modules.cupom.repository;

import org.cupinchacupons.backend.modules.entity.CupomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CupomRepository extends JpaRepository<CupomEntity, UUID> {
}
