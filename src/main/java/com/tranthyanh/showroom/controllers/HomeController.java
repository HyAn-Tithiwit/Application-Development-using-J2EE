package com.tranthyanh.showroom.controllers;

import com.tranthyanh.showroom.services.SettingsService;
import com.tranthyanh.showroom.controllers.Auth;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final SettingsService settingsService;
    private final Auth auth;

    // Inject các Service cần thiết
    public HomeController(SettingsService settingsService, Auth auth) {
        this.settingsService = settingsService;
        this.auth = auth;
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        // 1. Lấy nội dung About từ DB/Service
        model.addAttribute("aboutContent", settingsService.getAboutContent());

        // 2. Kiểm tra trạng thái đăng nhập để hiện nút Edit
        model.addAttribute("isUserLoggedIn", auth.isUserLoggedIn(request));

        // 3. Lấy tên công ty (nếu có)
        model.addAttribute("companyName", settingsService.getCompanyName());

        return "home";
    }
}