package org.cupinchacupons.backend.modules.categoria.controller;


import org.cupinchacupons.backend.modules.categoria.useCase.CreateCategoriaUseCase;
import org.cupinchacupons.backend.modules.entity.CategoriaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/painel")
public class CategoriaCotroller {

    private final CreateCategoriaUseCase createCategoriaUseCase;

    public CategoriaCotroller(CreateCategoriaUseCase createCategoriaUseCase) {
        this.createCategoriaUseCase = createCategoriaUseCase;
    }

    @PostMapping("/categoria")
    public ResponseEntity<Object> createCategoria(@RequestBody CategoriaEntity categoriaEntity) {
        try {
            var result = this.createCategoriaUseCase.CreateCategoria(categoriaEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
