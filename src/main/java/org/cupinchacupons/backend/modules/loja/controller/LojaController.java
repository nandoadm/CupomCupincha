package org.cupinchacupons.backend.modules.loja.controller;


import org.cupinchacupons.backend.modules.entity.LojaEntity;
import org.cupinchacupons.backend.modules.loja.useCase.CreateLojaUseCase;
import org.cupinchacupons.backend.modules.loja.useCase.ListStoreUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LojaController {

    private final CreateLojaUseCase createLojaUseCase;

    private final ListStoreUseCase listStoreUseCase;

    public LojaController(CreateLojaUseCase createLojaUseCase, ListStoreUseCase listStoreUseCase) {
        this.createLojaUseCase = createLojaUseCase;
        this.listStoreUseCase = listStoreUseCase;
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

    @GetMapping("/listarLoja")
    public ResponseEntity<? extends Object> listarLoja(@RequestParam(required = false) String filtro){
        try{
            var result = this.listStoreUseCase.listStore(filtro);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
