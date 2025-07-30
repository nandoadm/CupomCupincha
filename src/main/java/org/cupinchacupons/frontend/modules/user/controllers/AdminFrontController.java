package org.cupinchacupons.frontend.modules.user.controllers;


import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.cupinchacupons.frontend.modules.user.usecase.CreateCupomFrontService;
import org.cupinchacupons.frontend.modules.user.usecase.ListAllCoupunsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminFrontController {

    @Autowired
    private CreateCupomFrontService createCupomFrontService;

    @Autowired
    private ListAllCoupunsService listAllCoupunsService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "users/admin";
    }

    @GetMapping("/{secao}")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(
            @PathVariable(name = "secao", required = false) String secao,
            Model model) {
        model.addAttribute("secao", secao);
        return "users/admin";
    }

    @GetMapping("/create-cupom")
    public CouponResponseDTO execute(CouponResponseDTO couponResponseDTO) {
        var result = this.createCupomFrontService.createCupom(couponResponseDTO);
        return result;
    };


    @GetMapping("/posts")
    @PreAuthorize("hasRole('ADMIN')")
    public String listarPosts(@RequestParam(required = false) String filter, Model model) {

        model.addAttribute("secao", "posts");

        if (filter != null && !filter.isBlank()) {
            var coupons = listAllCoupunsService.listCoupouns(filter);
            model.addAttribute("coupons", coupons);
        } else {
            System.out.println("Filtro não informado, não buscar!");
        }

        System.out.println("Valor do filtro: " + filter);
        return "users/admin";
    }



}
