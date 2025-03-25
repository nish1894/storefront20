package com.storefront.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.storefront.entities.User;
import com.storefront.forms.UserForm;
import com.storefront.services.UserService;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

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
    public String register(Model model){
        UserForm userForm = new UserForm();

        model.addAttribute("userForm",userForm);


        return "register"; 
    }

    // processing register
    @RequestMapping(value = "/do-register", method =RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm){
        System.out.println("Processing Registration form");


        User user = new User();
        
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setPassword(userForm.getPassword());

        User savedUser = userService.saveUser(user);
        System.out.println("user saved with ID: " + savedUser.getUserId());
        


        // user.setDOB(userForm.getDOB());



        return "redirect:/register"; 
    }
}
 