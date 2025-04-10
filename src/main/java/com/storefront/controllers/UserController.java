package com.storefront.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storefront.entities.User;
import com.storefront.helpers.Helper;
import com.storefront.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @RequestMapping("/profile")
    public String home(Model model, Authentication authentication){

        String username = Helper.getEmailOfLoggedInUser(authentication); 
        User user = userService.getUserByEmail(username); 

        model.addAttribute("user", user); 


        return "user/profile"; 
    }

    @RequestMapping("/cart")
    public String cart(){

       
        return "user/cart"; 
    }

    @RequestMapping("/checkout")
    public String checkout(){

       
        return "user/checkout"; 
    }



 
}
