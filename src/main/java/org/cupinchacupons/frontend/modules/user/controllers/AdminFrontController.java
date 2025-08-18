package org.cupinchacupons.frontend.modules.user.controllers;


import org.cupinchacupons.frontend.modules.user.usecase.CreateCupomService;
import org.cupinchacupons.frontend.modules.user.usecase.ListAllCoupunsService;
import org.cupinchacupons.frontend.modules.user.usecase.ListCategoriaService;
import org.cupinchacupons.frontend.modules.user.usecase.ListStoreService;
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

    private final CreateCupomService createCupomFrontService;

    private final ListAllCoupunsService listAllCoupunsService;

    private final ListCategoriaService listCategoriaService;

    private final ListStoreService listStoreService;
    public AdminFrontController(CreateCupomService createCupomFrontService, ListAllCoupunsService listAllCoupunsService, ListCategoriaService listCategoriaService, ListStoreService listStoreService) {
        this.createCupomFrontService = createCupomFrontService;
        this.listAllCoupunsService = listAllCoupunsService;
        this.listCategoriaService = listCategoriaService;
        this.listStoreService = listStoreService;
    }

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

//    @GetMapping("/create-cupom")
//    public CouponResponseDTO execute(CouponResponseDTO couponResponseDTO) {
//        var result = this.createCupomFrontService.createCupom(couponResponseDTO);
//        return result;
//    };


    @GetMapping("/posts")
    @PreAuthorize("hasRole('ADMIN')")
    public String listarPosts(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String tipoFilter,
            Model model) {

        model.addAttribute("secao", "posts");
        model.addAttribute("filter", filter);
        model.addAttribute("opcao", tipoFilter);

        if (tipoFilter == null || tipoFilter.isBlank()) {
            tipoFilter = "Cupons"; // valor padrão se não for enviado
        }

        switch (tipoFilter) {
            case "Cupons":
                if (filter != null && !filter.isBlank()) {
                    var coupons = listAllCoupunsService.listCoupouns(filter);
                    model.addAttribute("coupons", coupons);
                } else {
                    var all = listAllCoupunsService.listCoupouns(filter);
                    model.addAttribute("coupons", all);
                }
                break;

            case "Categorias":
                if (filter != null && !filter.isBlank()) {
                    var categoria = listCategoriaService.execute(filter);
                    model.addAttribute("categoria", categoria);
                } else {
                    var allCategorias = listCategoriaService.execute(filter);
                    model.addAttribute("categoria", allCategorias);
                }
                break;

            case "Lojas":
                if(filter != null && !filter.isBlank()){
                    var lojas = listStoreService.execute(filter);
                    model.addAttribute("loja", lojas);
                } else {
                    var allLojas = listStoreService.execute(filter);
                    model.addAttribute("loja", allLojas);
                }
                break;
            // Adicione aqui os outros tipos: Lojas, Afiliados, Users...
            default:
                break;
        }

        return "users/admin";
    }


}


