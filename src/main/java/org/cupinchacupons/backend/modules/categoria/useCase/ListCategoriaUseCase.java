package org.cupinchacupons.backend.modules.categoria.useCase;

import org.cupinchacupons.backend.modules.categoria.dto.CategoriaResponseDTO;
import org.cupinchacupons.backend.modules.categoria.repository.CategoriaRepository;
import org.cupinchacupons.backend.modules.entity.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListCategoriaUseCase {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> execute(String filtro) {
        List<CategoriaEntity> categorias;

        if (filtro == null || filtro.isBlank()) {
            categorias = categoriaRepository.findAll();
        } else {
            categorias = categoriaRepository.findAllByDescricaoContainingIgnoreCase(filtro);
        }

        return categorias.stream().map(categoria ->
                CategoriaResponseDTO.builder()
                        .descricao(categoria.getDescricao())
                        .build()
        ).collect(Collectors.toList());
    }
}
