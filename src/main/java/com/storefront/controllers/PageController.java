package com.storefront.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    
    @RequestMapping("/home")
    public String home(Model model){

        System.out.println("home page handler ");
        model.addAttribute("name", "Nishant");

        return "home"; 
    }

    @RequestMapping("/contact")
    public String contact(){

        System.out.println("contact page handler ");

        return "contact"; 
    }

    @RequestMapping("/login")
    public String login(){

        System.out.println("login page handler ");

        return "login"; 
    }

    @RequestMapping("/register")
    public String register(){

        System.out.println("Signup page handler ");

        return "register"; 
    }
}
