package org.example.bankingportal.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Controller // controller for spring mvc, RestController for REST services
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if(principal != null) {
            model.addAttribute("username", principal.getAttribute("name"));

        }else{
            model.addAttribute("username", "guest");
        }
        return "home";
    }

//    @GetMapping("/home")
//    public Map<String, Object> home(@AuthenticationPrincipal OAuth2User principal) {
//        return Collections.singletonMap("name", principal.getAttribute("name")); // returns an immutable map!
//    }
}
