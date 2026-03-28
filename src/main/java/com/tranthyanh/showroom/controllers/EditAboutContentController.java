package com.tranthyanh.showroom.controllers;

import com.tranthyanh.showroom.services.SettingsService;
import com.tranthyanh.showroom.controllers.Auth;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditAboutContentController {
    private final Auth auth;
    private final SettingsService settingsService;

    public EditAboutContentController(Auth auth, SettingsService settingsService) {
        this.auth = auth;
        this.settingsService = settingsService;
    }

    @GetMapping("/edit-about-content")
    public String get(HttpServletRequest request, Model model) {
        if (!auth.isUserLoggedIn(request)) {
            return "redirect:/login"; // [cite: 319]
        }
        model.addAttribute("isUserLoggedIn", true)
                .addAttribute("companyName", settingsService.getCompanyName())
                .addAttribute("aboutContent", settingsService.getAboutContent()); // [cite: 319]
        return "edit-about-content";
    }

    @PostMapping("/edit-about-content")
    public String post(@RequestParam String aboutContent, HttpServletRequest request, Model model) {
        if (!auth.isUserLoggedIn(request)) {
            return "redirect:/login"; // [cite: 324]
        }
        try {
            settingsService.updateAboutContent(aboutContent); // [cite: 324]
            return "redirect:/#about"; // [cite: 324]
        } catch (Exception e) {
            model.addAttribute("isUserLoggedIn", true)
                    .addAttribute("companyName", settingsService.getCompanyName())
                    .addAttribute("aboutContent", aboutContent)
                    .addAttribute("errorMsg", e.getMessage()); // [cite: 324]
            return "edit-about-content";
        }
    }
}