package org.cupinchacupons.backend.modules.cupom.useCase;


import org.cupinchacupons.backend.modules.cupom.repository.CupomRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletCoupounUseCase {

    private final CupomRepository cupomRepository;

    public DeletCoupounUseCase(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public void deleteCoupon(UUID id) {
        try {
        var coupon = cupomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
        cupomRepository.delete(coupon);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting coupon");
        }


    }
}
