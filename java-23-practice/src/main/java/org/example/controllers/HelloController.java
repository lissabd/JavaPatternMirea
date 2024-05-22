package org.example.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // Изменение здесь
        String username = userDetails.getUsername(); // Изменение здесь
        model.addAttribute("username", username);
        return "hello";
    }
}

