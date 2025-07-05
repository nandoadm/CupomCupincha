package org.cupinchacupons.backend.modules.afiliado.useCase;


import org.cupinchacupons.backend.modules.afiliado.repository.AfiliadoRepository;
import org.cupinchacupons.backend.modules.entity.AfiliadoEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateAfiliadoUseCase {

    private final AfiliadoRepository afiliadoRepository;

    public CreateAfiliadoUseCase(AfiliadoRepository afiliadoRepository) {
        this.afiliadoRepository = afiliadoRepository;
    }

    public AfiliadoEntity execute(AfiliadoEntity afiliadoEntity) {
        this.afiliadoRepository.findByCnpj(afiliadoEntity.getCnpj())
                .ifPresent(afiliado -> {
                    throw new RuntimeException("Afiliado ja existe");
                });


        return this.afiliadoRepository.save(afiliadoEntity);
    }
}
