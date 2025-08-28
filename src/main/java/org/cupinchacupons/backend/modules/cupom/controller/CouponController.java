package org.cupinchacupons.backend.modules.cupom.controller;


import org.cupinchacupons.backend.modules.cupom.dto.CouponRequestDTO;
import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.cupinchacupons.backend.modules.cupom.useCase.CreateCouponUseCase;
import org.cupinchacupons.backend.modules.cupom.useCase.DeletCoupounUseCase;
import org.cupinchacupons.backend.modules.cupom.useCase.ListCouponsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CouponController {

    private final ListCouponsUseCase listCouponsUseCase;

    private final CreateCouponUseCase createCouponUseCase;

    private final DeletCoupounUseCase deletCoupounUseCase;

    public CouponController(ListCouponsUseCase listCouponsUseCase, CreateCouponUseCase createCouponUseCase, DeletCoupounUseCase deletCoupounUseCase) {
        this.listCouponsUseCase = listCouponsUseCase;
        this.createCouponUseCase = createCouponUseCase;
        this.deletCoupounUseCase = deletCoupounUseCase;
    }


    @PostMapping("/create-cupom")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> create(@RequestBody CouponRequestDTO couponRequestDTO) {
        try {
            var result = this.createCouponUseCase.execute(couponRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> listarCupons(@RequestParam String filtro) {
        try {
        List<CouponResponseDTO> result = listCouponsUseCase.listAllCoupons(filtro);
        return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("list-final-coupon")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> listFinalCoupons() {
        try {
            var coupom = this.listCouponsUseCase.listFinalCoupons();
            return ResponseEntity.ok().body(coupom);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-coupon/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deletarCupom(@PathVariable UUID id) {
        try {
            this.deletCoupounUseCase.deleteCoupon(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
