package org.example.bankingportal.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
 class RedirectController {

    @GetMapping("/redirect/home")
    public String home(Model model, @AuthenticationPrincipal Jwt principal){
        if(principal != null) {
            model.addAttribute("user", principal.getClaim("username"));

        }else{
            model.addAttribute("user", "guest");
        }
        return "home";
    }
}
