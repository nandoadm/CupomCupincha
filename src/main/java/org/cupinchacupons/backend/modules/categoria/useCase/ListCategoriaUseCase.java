/* <<<<<<<<<<<<<<  ✨ Windsurf Command 🌟 >>>>>>>>>>>>>>>> */
package org.cupinchacupons.backend.modules.categoria.useCase;

import org.cupinchacupons.backend.modules.categoria.dto.CategoriaResponseDTO;
import org.cupinchacupons.backend.modules.categoria.repository.CategoriaRepository;
import org.cupinchacupons.backend.modules.entity.CategoriaEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public ListCategoriaUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponseDTO> execute(String Filter) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        List<CategoriaEntity> categorias = ((Filter == null || Filter.isEmpty())
                ? categoriaRepository.findAll()
                : categoriaRepository.findAllByDescricaoContainingIgnoreCase(Filter));

        return categorias.stream()
                .sorted(
                        Comparator.comparing(c -> c.getDescricao().toLowerCase())
                )
                .map(categoria ->
                        CategoriaResponseDTO.builder()
                                .id(categoria.getId())
                                .descricao(categoria.getDescricao())
                                .createdAt(categoria.getCreatedAt() != null
                                        ? categoria.getCreatedAt().format(formatter)
                                        : null)
                                .build()
                )
                .collect(Collectors.toList());
    }
}
