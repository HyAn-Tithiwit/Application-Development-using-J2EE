package com.tranthyanh.showroom.controllers;

import com.tranthyanh.showroom.services.SettingsService;
import com.tranthyanh.showroom.controllers.Auth;
import com.tranthyanh.showroom.services.ProductsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final Auth auth;
    private final SettingsService settingsService;
    private final ProductsService productsService;

    // Inject các Service cần thiết
    public HomeController(Auth auth, SettingsService settingsService, ProductsService productsService) {
        this.auth = auth;
        this.settingsService = settingsService;
        this.productsService = productsService;
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("isUserLoggedIn", auth.isUserLoggedIn(request))
                .addAttribute("companyName", settingsService.getCompanyName())
                .addAttribute("products", productsService.getProducts())
                .addAttribute("aboutContent", settingsService.getAboutContent());
        return "home";
    }
}