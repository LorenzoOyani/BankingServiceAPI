package org.example.bankingportal.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankingportal.entities.Messages;
import org.example.bankingportal.service.MessageClientService;
import org.example.bankingportal.service.SecurityHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller // controller for spring mvc, RestController for REST services
public class HomeController {

    private final MessageClientService messageClientService;
    private final SecurityHelper securityHelper;

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if(principal != null) {
            model.addAttribute("user", principal.getAttribute("username"));

        }else{
            model.addAttribute("user", "guest");
        }

        List<Messages> messages = messageClientService.getMessages();
        model.addAttribute("messages", messages);
        
        return "home";
    }

    @PostMapping("/messages")
    public String createMessage(Messages message) {
        Map<String, Object> loggedUserDetails = securityHelper.getLoggedUerDetails();
        message.setCreatedBy(loggedUserDetails.get("username").toString());
        messageClientService.createMessage(message);
        return "redirect:/home";
    }



}
