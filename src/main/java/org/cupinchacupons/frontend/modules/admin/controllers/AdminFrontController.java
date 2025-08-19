package org.cupinchacupons.frontend.modules.admin.controllers;


import org.cupinchacupons.frontend.modules.admin.afiliado.service.ListAfiliadoService;
import org.cupinchacupons.frontend.modules.admin.categoria.service.ListCategoriaService;
import org.cupinchacupons.frontend.modules.admin.cupom.service.CreateCupomService;
import org.cupinchacupons.frontend.modules.admin.cupom.service.ListAllCoupunsService;
import org.cupinchacupons.frontend.modules.admin.loja.service.ListStoreService;
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

    private final ListAfiliadoService listAfiliadoService;

    private final ListStoreService listStoreService;
    public AdminFrontController(CreateCupomService createCupomFrontService, ListAllCoupunsService listAllCoupunsService, ListCategoriaService listCategoriaService, ListAfiliadoService listAfiliadoService, ListStoreService listStoreService) {
        this.createCupomFrontService = createCupomFrontService;
        this.listAllCoupunsService = listAllCoupunsService;
        this.listCategoriaService = listCategoriaService;
        this.listAfiliadoService = listAfiliadoService;
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
                var coupons = listAllCoupunsService.listCoupouns(filter);
                model.addAttribute("coupons", coupons);
                break;

            case "Categorias":
                var allCategorias = listCategoriaService.execute(filter);
                model.addAttribute("categoria", allCategorias);
                break;

            case "Lojas":
                var allLojas = listStoreService.execute(filter);
                model.addAttribute("loja", allLojas);
                break;

            case "Afiliados":
                var allAfiliados = listAfiliadoService.listAfiliados(filter);
                model.addAttribute("afiliado", allAfiliados);
                break;
            // Adicione aqui os outros tipos: Lojas, Afiliados, Users...
            default:
                break;
        }

        return "users/admin";
    }


}