package org.cupinchacupons.backend.modules.loja.controller;


import org.cupinchacupons.backend.modules.entity.LojaEntity;
import org.cupinchacupons.backend.modules.loja.useCase.CreateLojaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/painel")
public class LojaController {

    private final CreateLojaUseCase createLojaUseCase;

    public LojaController(CreateLojaUseCase createLojaUseCase) {
        this.createLojaUseCase = createLojaUseCase;
    }

    @PostMapping("/loja")
    public ResponseEntity<Object> createLoja(@RequestBody LojaEntity lojaEntity) {
        try {
            var result = this.createLojaUseCase.createLoja(lojaEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
