package org.cupinchacupons.backend.modules.cupom.useCase;


import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.cupinchacupons.backend.modules.cupom.repository.CupomRepository;
import org.cupinchacupons.backend.modules.entity.CupomEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListCouponsUseCase {

    private final CupomRepository cupomRepository;

    public ListCouponsUseCase(CupomRepository cupomRepository) {
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
                .img_url(CupomResponse.getImg_url())
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

    public CouponResponseDTO listFinalCoupons(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        CupomEntity lastCoupon = cupomRepository.findAllByValidadeLessThan(LocalDateTime.now()).stream().max(Comparator.comparing(CupomEntity::getValidade)).orElse(null);

        return lastCoupon == null ?(CouponResponseDTO.builder()
                .id(lastCoupon.getId())
                .ativo(String.valueOf(lastCoupon.getAtivo()))
                .titulo(lastCoupon.getTitulo())
                .validade(lastCoupon.getValidade() != null ? lastCoupon.getValidade().toString() : null)
                .descricao(lastCoupon.getDescricao())
                .Codigo(lastCoupon.getCodigo())
                .slug(lastCoupon.getSlug())
                .img_url(lastCoupon.getImg_url())
                .createdAt(lastCoupon.getCreated_At() != null
                        ? lastCoupon.getCreated_At().format(formatter)
                        : null)
                .afiliadoNome(lastCoupon.getAfiliado() != null
                        ? lastCoupon.getAfiliado().getNome()
                        : null)
                .lojaNome(lastCoupon.getLoja() != null
                        ? lastCoupon.getLoja().getNome()
                        : null)
                .categoriaDescricao(lastCoupon.getCategoria() != null
                        ? lastCoupon.getCategoria().getDescricao()
                        : null)
                .build())
                : null;
    }
}