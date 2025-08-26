package org.cupinchacupons.backend.modules.cupom.controller;


import org.cupinchacupons.backend.modules.cupom.dto.CouponRequestDTO;
import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.cupinchacupons.backend.modules.cupom.useCase.CreateCupomUseCase;
import org.cupinchacupons.backend.modules.cupom.useCase.ListAllCouponsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CupomControlller {

    private final ListAllCouponsUseCase listAllCouponsUseCase;

    private final CreateCupomUseCase createCupomUseCase;

    public CupomControlller(ListAllCouponsUseCase listAllCouponsUseCase, CreateCupomUseCase createCupomUseCase) {
        this.listAllCouponsUseCase = listAllCouponsUseCase;
        this.createCupomUseCase = createCupomUseCase;
    }


    @PostMapping("/create-cupom")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> create(@RequestBody CouponRequestDTO couponRequestDTO) {
        try {
            var result = this.createCupomUseCase.execute(couponRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<? extends Object> listarCupons(@RequestParam String filtro) {
        try {
        List<CouponResponseDTO> result = listAllCouponsUseCase.listAllCoupons(filtro);
        return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
