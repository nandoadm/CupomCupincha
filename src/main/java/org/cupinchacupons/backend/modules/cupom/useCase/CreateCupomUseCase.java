package org.cupinchacupons.backend.modules.cupom.useCase;


import org.cupinchacupons.backend.modules.cupom.repository.CupomRepository;
import org.cupinchacupons.backend.modules.entity.CupomEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateCupomUseCase {

    private final CupomRepository cupomRepository;

    public CreateCupomUseCase(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public CupomEntity execute(CupomEntity cupomEntity) {
        return this.cupomRepository.save(cupomEntity);
    }
}
