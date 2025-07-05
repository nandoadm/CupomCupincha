package org.cupinchacupons.backend.modules.afiliado.controller;


import org.cupinchacupons.backend.modules.afiliado.useCase.CreateAfiliadoUseCase;
import org.cupinchacupons.backend.modules.entity.AfiliadoEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/painel")
public class AfiliadoController {

    private final CreateAfiliadoUseCase createAfiliadoUseCase;

    public AfiliadoController(CreateAfiliadoUseCase createAfiliadoUseCase) {
        this.createAfiliadoUseCase = createAfiliadoUseCase;
    }

    @PostMapping("/afiliado")
    public ResponseEntity<Object> createAfiliado(@RequestBody AfiliadoEntity afiliadoEntity) {
        try{
        var result = this.createAfiliadoUseCase.execute(afiliadoEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
