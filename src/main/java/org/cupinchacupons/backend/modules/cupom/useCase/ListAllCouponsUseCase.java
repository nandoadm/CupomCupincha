package org.cupinchacupons.backend.modules.cupom.useCase;


import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.cupinchacupons.backend.modules.cupom.repository.CupomRepository;
import org.cupinchacupons.backend.modules.entity.CupomEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllCouponsUseCase {

    private final CupomRepository cupomRepository;

    public ListAllCouponsUseCase(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public List<CouponResponseDTO> listAllCoupons(String Filter) {

        if (Filter == null || Filter.isEmpty()) {
            List<CupomEntity> cupom =cupomRepository.findAll();

            return cupom.stream().map(CupomResponse -> CouponResponseDTO.builder()
                    .ativo(String.valueOf(CupomResponse.getAtivo()))
                    .titulo(CupomResponse.getTitulo())
                    .validade(String.valueOf(CupomResponse.getValidade()))
                    .descricao(CupomResponse.getDescricao())
                    .Codigo(CupomResponse.getCodigo())
                    .categotiaDescricao(CupomResponse.getCategoria() != null ? CupomResponse.getCategoria().getDescricao() : null)
                    .build()
            ).collect(Collectors.toList());
        } else {

            List<CupomEntity> cupom = cupomRepository.findAllByTituloContainingIgnoreCase(Filter);

            return cupom.stream().map(CupomResponse -> CouponResponseDTO.builder()
                    .ativo(String.valueOf(CupomResponse.getAtivo()))
                    .titulo(CupomResponse.getTitulo())
                    .validade(String.valueOf(CupomResponse.getValidade()))
                    .descricao(CupomResponse.getDescricao())
                    .Codigo(CupomResponse.getCodigo())
                    .categotiaDescricao(CupomResponse.getCategoria() != null ? CupomResponse.getCategoria().getDescricao() : null)
                    .build()
            ).collect(Collectors.toList());
        }
    }
}
