package org.cupinchacupons.backend.modules.cupom.useCase;

import org.cupinchacupons.backend.modules.afiliado.repository.AfiliadoRepository;
import org.cupinchacupons.backend.modules.categoria.repository.CategoriaRepository;
import org.cupinchacupons.backend.modules.cupom.dto.CouponRequestDTO;
import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.cupinchacupons.backend.modules.cupom.repository.CupomRepository;
import org.cupinchacupons.backend.modules.entity.CupomEntity;
import org.cupinchacupons.backend.modules.loja.repository.LojaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CreateCouponUseCase {

    private final CupomRepository cupomRepository;
    private final CategoriaRepository categoriaRepository;
    private final AfiliadoRepository afiliadoRepository;
    private final LojaRepository lojaRepository;

    public CreateCouponUseCase(CupomRepository cupomRepository,
                               CategoriaRepository categoriaRepository,
                               AfiliadoRepository afiliadoRepository,
                               LojaRepository lojaRepository) {
        this.cupomRepository = cupomRepository;
        this.categoriaRepository = categoriaRepository;
        this.afiliadoRepository = afiliadoRepository;
        this.lojaRepository = lojaRepository;
    }

    public CouponResponseDTO execute(CouponRequestDTO dto) {

        var categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        var afiliado = afiliadoRepository.findById(dto.getAfiliadoId())
                .orElseThrow(() -> new RuntimeException("Afiliado não encontrado"));

        var loja = lojaRepository.findById(dto.getLojaId())
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        // Converte string de date para LocalDateTime
        LocalDateTime validade = LocalDate.parse(dto.getValidade()).atStartOfDay();

        CupomEntity entity = CupomEntity.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .codigo(dto.getCodigo())
                .validade(validade)
                .ativo(Integer.parseInt(dto.getAtivo()))
                .restricoes(dto.getRestricoes())
                .slug(dto.getSlug())
                .desconto(dto.getDesconto())
                .categoria(categoria)
                .afiliado(afiliado)
                .loja(loja)
                .build();

        var saved = cupomRepository.save(entity);

        return CouponResponseDTO.builder()
                .id(saved.getId())
                .titulo(saved.getTitulo())
                .descricao(saved.getDescricao())
                .Codigo(saved.getCodigo())
                .validade(saved.getValidade().toString())
                .ativo(String.valueOf(saved.getAtivo()))
                .slug(saved.getSlug())
                .createdAt(saved.getCreated_At().toString())
                .categoriaDescricao(saved.getCategoria().getDescricao())
                .afiliadoNome(saved.getAfiliado().getNome())
                .lojaNome(saved.getLoja().getNome())
                .createdAt(saved.getCreated_At().toString())
                .build();
    }
}
