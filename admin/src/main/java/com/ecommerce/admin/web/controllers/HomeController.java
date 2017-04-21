package com.ecommerce.admin.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dguzik
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(Model model)
    {
        return "home";
    }
}
