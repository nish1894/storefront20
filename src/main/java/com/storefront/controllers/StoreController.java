package com.storefront.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {

    @RequestMapping("/home")
    public String home(Model model){

        System.out.println("home page handler ");
        model.addAttribute("name", "Nishant");
        return "store/home"; 
    }
}
