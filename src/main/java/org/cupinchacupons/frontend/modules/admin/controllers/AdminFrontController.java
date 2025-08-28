package org.cupinchacupons.frontend.modules.admin.controllers;

import org.cupinchacupons.backend.modules.afiliado.repository.AfiliadoRepository;
import org.cupinchacupons.backend.modules.categoria.repository.CategoriaRepository;
import org.cupinchacupons.backend.modules.cupom.dto.CouponRequestDTO;
import org.cupinchacupons.backend.modules.loja.repository.LojaRepository;
import org.cupinchacupons.frontend.modules.admin.afiliado.service.ListAfiliadoService;
import org.cupinchacupons.frontend.modules.admin.categoria.service.ListCategoriaService;
import org.cupinchacupons.frontend.modules.admin.cupom.service.CreateCupomService;
import org.cupinchacupons.frontend.modules.admin.cupom.service.ListAllCoupunsService;
import org.cupinchacupons.frontend.modules.admin.cupom.service.ListFinalCouponService;
import org.cupinchacupons.frontend.modules.admin.loja.service.ListStoreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminFrontController {

    private final CreateCupomService createCupomFrontService;
    private final ListAllCoupunsService listAllCoupunsService;
    private final ListCategoriaService listCategoriaService;
    private final ListAfiliadoService listAfiliadoService;
    private final ListStoreService listStoreService;
    private final CategoriaRepository categoriaRepository;
    private final AfiliadoRepository afiliadoRepository;
    private final LojaRepository lojaRepository;
    private final ListFinalCouponService listFinalCouponService;

    public AdminFrontController(
            CreateCupomService createCupomFrontService,
            ListAllCoupunsService listAllCoupunsService,
            ListCategoriaService listCategoriaService,
            ListAfiliadoService listAfiliadoService,
            ListStoreService listStoreService,
            CategoriaRepository categoriaRepository,
            AfiliadoRepository afiliadoRepository,
            LojaRepository lojaRepository, ListFinalCouponService listFinalCouponService
    ) {
        this.createCupomFrontService = createCupomFrontService;
        this.listAllCoupunsService = listAllCoupunsService;
        this.listCategoriaService = listCategoriaService;
        this.listAfiliadoService = listAfiliadoService;
        this.listStoreService = listStoreService;
        this.categoriaRepository = categoriaRepository;
        this.afiliadoRepository = afiliadoRepository;
        this.lojaRepository = lojaRepository;
        this.listFinalCouponService = listFinalCouponService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "users/admin";
    }

    @GetMapping("/{secao}")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminSecao(@PathVariable String secao, Model model) {
        model.addAttribute("secao", secao);
        return "users/admin";
    }

    @GetMapping("/posts")
    @PreAuthorize("hasRole('ADMIN')")
    public String listarPosts(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String tipoFilter,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("secao", "posts");

        // Valor padrão se não for enviado
        if (tipoFilter == null || tipoFilter.isBlank()) {
            tipoFilter = "Cupons";
        }

        model.addAttribute("filter", filter);
        model.addAttribute("opcao", tipoFilter);

        switch (tipoFilter) {
            case "Cupons":
                var coupons = listAllCoupunsService.listCoupouns(filter);
                model.addAttribute("coupons", coupons);

                if (coupons.isEmpty()) {
                    model.addAttribute("filter_error", true);
                    model.addAttribute("message_error", "Nenhum cupom encontrado com o filtro '" + filter + "'");
                }

                break;
            case "Categorias":
                var categorias = listCategoriaService.listCategoria(filter);
                model.addAttribute("categorias", categorias);
                if (categorias.isEmpty()) {
                    model.addAttribute("filter_error", true);
                    model.addAttribute("message_error", "Nenhuma categoria encontrado com o filtro '" + filter + "'");
                }
                break;
            case "Lojas":
                var lojas = listStoreService.execute(filter);
                model.addAttribute("lojas", lojas);
                if (lojas.isEmpty()) {
                    model.addAttribute("filter_error", true);
                    model.addAttribute("message_error", "Nenhuma loja encontrado com o filtro '" + filter + "'");
                }

                break;
            case "Afiliados":
                var afiliados = listAfiliadoService.listAfiliados(filter);
                model.addAttribute("afiliados", afiliados);
                if (afiliados.isEmpty()) {
                    model.addAttribute("filter_error", true);
                    model.addAttribute("message_error", "Nenhum afiliado encontrado com o filtro '" + filter + "'");
                }
                break;
            default:
                break;
        }

        return "users/admin";
    }

    @GetMapping("/create-cupom")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage(Model model) {

        model.addAttribute("secao", "create-cupom");
        model.addAttribute("afiliados", listAfiliadoService.listAfiliados(null));
        model.addAttribute("categorias", listCategoriaService.listCategoria(null));
        model.addAttribute("lojas", listStoreService.execute(null));

        return "users/admin";
    }

    @PostMapping("/create-cupom")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveCoupon(@ModelAttribute CouponRequestDTO couponRequestDTO, RedirectAttributes redirectAttributes) {
        // Cria o cupom
        var result = createCupomFrontService.createCupom(couponRequestDTO);

        // Busca o último cupom criado
        var lastCoupon = listFinalCouponService.listFinalCoupons();

        if (result == null) {
            redirectAttributes.addFlashAttribute("message_error", "Erro ao criar cupom");
        } else {
            redirectAttributes.addFlashAttribute("message_created", "Cupom criado com sucesso!");
            redirectAttributes.addFlashAttribute("lastCoupon", result);
        }

        return "redirect:/admin/create-cupom";
    }


    @ModelAttribute("cupom")
    public CouponRequestDTO couponRequestDTO() {
        return new CouponRequestDTO();
    }
}