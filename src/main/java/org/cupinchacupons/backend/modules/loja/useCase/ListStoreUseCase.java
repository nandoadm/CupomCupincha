package org.cupinchacupons.backend.modules.loja.useCase;


import org.cupinchacupons.backend.modules.entity.LojaEntity;
import org.cupinchacupons.backend.modules.loja.dto.StoreResponseDTO;
import org.cupinchacupons.backend.modules.loja.repository.LojaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListStoreUseCase {

    private final LojaRepository lojaRepository;

    public ListStoreUseCase(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public List<StoreResponseDTO> listStore(String filtro){

        List<LojaEntity> lojas;

        if(filtro == null || filtro.isBlank()){
            lojas = this.lojaRepository.findAll();
        } else {
            lojas = this.lojaRepository.findByNomeContainingIgnoreCase(filtro);
        }

        return lojas.stream()
                .sorted(Comparator.comparing(LojaEntity::getNome))
                .map(loja ->
                        StoreResponseDTO.builder()
                                .nome(loja.getNome())
                                .url(loja.getUrl())
                                .build())
                .collect(Collectors.toList());
    }
}

