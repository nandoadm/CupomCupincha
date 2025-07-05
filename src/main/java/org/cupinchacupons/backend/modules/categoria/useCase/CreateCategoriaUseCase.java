package org.cupinchacupons.backend.modules.categoria.useCase;


import org.cupinchacupons.backend.modules.categoria.repository.CategoriaRepository;
import org.cupinchacupons.backend.modules.entity.CategoriaEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public CreateCategoriaUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaEntity CreateCategoria(CategoriaEntity categoriaEntity) {
        return this.categoriaRepository.save(categoriaEntity);
    }
}
