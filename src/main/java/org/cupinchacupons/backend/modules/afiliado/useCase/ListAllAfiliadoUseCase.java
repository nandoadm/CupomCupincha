package org.cupinchacupons.backend.modules.afiliado.useCase;


import org.cupinchacupons.backend.modules.afiliado.dto.AfiliadoResponseDTO;
import org.cupinchacupons.backend.modules.afiliado.repository.AfiliadoRepository;
import org.cupinchacupons.backend.modules.entity.AfiliadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllAfiliadoUseCase {

    @Autowired
    public AfiliadoRepository afiliadoRepository;

    public List<AfiliadoResponseDTO> execute(String filtro) {
        List<AfiliadoEntity> afiliados;

        if (filtro == null || filtro.isBlank()) {
            afiliados = this.afiliadoRepository.findAll();
        } else {
            afiliados = this.afiliadoRepository.findAllByNomeContainingIgnoreCase(filtro);
        }
        
        return afiliados.stream()
                .sorted(Comparator.comparing(afiliado -> afiliado.getNome().toLowerCase()))
                .map(afiliado -> {
                    return AfiliadoResponseDTO.builder()
                            .nome(afiliado.getNome())
                            .cnpj(afiliado.getCnpj())
                            .createdAt(afiliado.getCreatedAt().toString())
                            .build();
                })
                .collect(Collectors.toList());

    }
}