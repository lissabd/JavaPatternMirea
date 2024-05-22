package org.example.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// класс который обрабатывает запросы (к главной странице)
public class HomeController {

    @GetMapping("/home")
    // указываетч то метод будет вызываться когда пользователь перейдет на урл с home
    // возвращает имя представляния которое нужно показать пользователю
    public String homePage() {
        return "index";
    }
}
