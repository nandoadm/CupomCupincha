package org.cupinchacupons.backend.modules.cupom.useCase;


import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.cupinchacupons.backend.modules.cupom.repository.CupomRepository;
import org.cupinchacupons.backend.modules.entity.CupomEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllCouponsUseCase {

    private final CupomRepository cupomRepository;

    public ListAllCouponsUseCase(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public List<CouponResponseDTO> listAllCoupons(String Filter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        List<CupomEntity> cupom = (Filter == null || Filter.isEmpty())
                ? cupomRepository.findAll()
                : cupomRepository.findAllByTituloContainingIgnoreCase(Filter);

        return cupom.stream().map(CupomResponse -> CouponResponseDTO.builder()
                .id(CupomResponse.getId())
                .ativo(String.valueOf(CupomResponse.getAtivo()))
                .titulo(CupomResponse.getTitulo())
                .validade(CupomResponse.getValidade() != null ? CupomResponse.getValidade().toString() : null)
                .descricao(CupomResponse.getDescricao())
                .Codigo(CupomResponse.getCodigo())
                .slug(CupomResponse.getSlug())
                .createdAt(CupomResponse.getCreated_At() != null
                        ? CupomResponse.getCreated_At().format(formatter)
                        : null)
                .afiliadoNome(CupomResponse.getAfiliado() != null
                        ? CupomResponse.getAfiliado().getNome()
                        : null)
                .lojaNome(CupomResponse.getLoja() != null
                        ? CupomResponse.getLoja().getNome()
                        : null)
                .categoriaDescricao(CupomResponse.getCategoria() != null
                        ? CupomResponse.getCategoria().getDescricao()
                        : null)
                .build()
        ).collect(Collectors.toList());
    }
}
