package org.cupinchacupons.backend.modules.cupom.controller;


import org.cupinchacupons.backend.modules.cupom.useCase.CreateCupomUseCase;
import org.cupinchacupons.backend.modules.entity.CupomEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/painel")
public class CupomControlller {

    private final CreateCupomUseCase createCupomUseCase;

    public CupomControlller(CreateCupomUseCase createCupomUseCase) {
        this.createCupomUseCase = createCupomUseCase;
    }


    @PostMapping("/cupom")
    public ResponseEntity<Object> create(@RequestBody CupomEntity cupomEntity) {
        try {
            var result = this.createCupomUseCase.execute(cupomEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
