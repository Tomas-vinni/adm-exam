package ru.dvinni.davito.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер главной страницы
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
