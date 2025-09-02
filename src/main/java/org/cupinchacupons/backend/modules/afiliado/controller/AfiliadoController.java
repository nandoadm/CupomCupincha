package org.cupinchacupons.backend.modules.afiliado.controller;


import org.cupinchacupons.backend.modules.afiliado.useCase.CreateAfiliadoUseCase;
import org.cupinchacupons.backend.modules.afiliado.useCase.ListAllAfiliadoUseCase;
import org.cupinchacupons.backend.modules.entity.AfiliadoEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AfiliadoController {

    private final CreateAfiliadoUseCase createAfiliadoUseCase;
    private final ListAllAfiliadoUseCase listAllAfiliadoUseCase;

    public AfiliadoController(CreateAfiliadoUseCase createAfiliadoUseCase, ListAllAfiliadoUseCase listAllAfiliadoUseCase) {
        this.createAfiliadoUseCase = createAfiliadoUseCase;
        this.listAllAfiliadoUseCase = listAllAfiliadoUseCase;
    }

    @PostMapping("/afiliado")
    public ResponseEntity<Object> createAfiliado(@RequestBody AfiliadoEntity afiliadoEntity) {
        try {
            var result = this.createAfiliadoUseCase.execute(afiliadoEntity);
            ResponseEntity<Object> body = ResponseEntity.ok().body(result);
            return body;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/listar-afiliado")
    public ResponseEntity<Object> listarAfiliado(@RequestParam(required = false) String filtro) {
        try {
            var result = this.listAllAfiliadoUseCase.execute(filtro);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}