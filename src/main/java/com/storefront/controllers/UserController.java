package com.storefront.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/profile")
    public String home(Model model){

        System.out.println("User profile page handler ");
        model.addAttribute("name", "Nishant");
        return "user/profile"; 
    }


}
