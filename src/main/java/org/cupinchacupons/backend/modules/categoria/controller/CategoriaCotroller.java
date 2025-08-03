package org.cupinchacupons.backend.modules.categoria.controller;


import org.cupinchacupons.backend.modules.categoria.dto.CategoriaResponseDTO;
import org.cupinchacupons.backend.modules.categoria.useCase.CreateCategoriaUseCase;
import org.cupinchacupons.backend.modules.categoria.useCase.ListCategoriaUseCase;
import org.cupinchacupons.backend.modules.entity.CategoriaEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoriaCotroller {

    private final CreateCategoriaUseCase createCategoriaUseCase;

    private final ListCategoriaUseCase listCategoriaUseCase;

    public CategoriaCotroller(CreateCategoriaUseCase createCategoriaUseCase, ListCategoriaUseCase listCategoriaUseCase) {
        this.createCategoriaUseCase = createCategoriaUseCase;
        this.listCategoriaUseCase = listCategoriaUseCase;
    }

    @PostMapping("/categoria")
    public ResponseEntity<Object> createCategoria(@RequestBody CategoriaEntity categoriaEntity) {
        try {
            var result = this.createCategoriaUseCase.createCategoria(categoriaEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listarCategoria")
    public ResponseEntity<? extends Object> listarCategoria(@RequestParam(required = false) String filter) {
        try {
            List<CategoriaResponseDTO> result = listCategoriaUseCase.execute(filter);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
