package org.cupinchacupons.backend.modules.loja.useCase;


import org.cupinchacupons.backend.modules.entity.LojaEntity;
import org.cupinchacupons.backend.modules.loja.repository.LojaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateLojaUseCase {

    private final LojaRepository lojaRepository;

    public CreateLojaUseCase(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public LojaEntity createLoja(LojaEntity lojaEntity) {

        this.lojaRepository.findByNome(lojaEntity.getNome())
                .ifPresent(loja -> {
            throw new RuntimeException("Loja ja existe");
        });

        return this.lojaRepository.save(lojaEntity);
    }

}
