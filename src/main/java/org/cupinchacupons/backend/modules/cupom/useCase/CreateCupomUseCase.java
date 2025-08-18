package org.cupinchacupons.backend.modules.cupom.useCase;


import org.cupinchacupons.backend.modules.cupom.dto.CouponRequestDTO;
import org.cupinchacupons.backend.modules.cupom.repository.CupomRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCupomUseCase {

    private final CupomRepository cupomRepository;

    public CreateCupomUseCase(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public CouponRequestDTO execute(CouponRequestDTO couponRequestDTO) {


        var exits = this.cupomRepository.findAllByTituloContainingIgnoreCase(couponRequestDTO.getTitulo());

        if (!exits.isEmpty()) {
            throw new RuntimeException("cupom ja existe");
        }

        return CouponRequestDTO.builder()
                .desconto(couponRequestDTO.getDesconto())
                .restricoes(couponRequestDTO.getRestricoes())
                .slug(couponRequestDTO.getSlug())
                .ativo(couponRequestDTO.getAtivo())
                .codigo(couponRequestDTO.getCodigo())
                .titulo(couponRequestDTO.getTitulo())
                .validade(couponRequestDTO.getValidade())
                .descricao(couponRequestDTO.getDescricao())
                .build();

    }
}
