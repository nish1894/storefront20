package com.storefront.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storefront.entities.CartItems;
import com.storefront.entities.Items;
import com.storefront.helpers.SessionCart;
import com.storefront.services.ItemsService;

@Controller
@RequestMapping("/store")
public class StoreController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private SessionCart sessionCart; // Let Spring inject the managed bean

    

    @RequestMapping("/home")
    public String home(Model model){

        List<Items> allItems = itemsService.getAll(); 

        // Print items to terminal
        // logger.info("All items retrieved: {}", allItems);
         

        model.addAttribute("items", allItems);
        
        
        return "store/home"; 
    }


    @RequestMapping("/home2")
    public String home2(Model model){

        List<Items> allItems = itemsService.getAll(); 

    
        
        model.addAttribute("items", allItems);
        
        
        return "store/home2"; 
    }



    @RequestMapping("/cart")
    public String cart(Model model){

        List<Items> allCartItems = sessionCart.getAllItemsOfCart();

         // Print items to terminal
        logger.info("All items retrieved: {}", allCartItems);

        // List<Items> allItems = itemsService.getAll(); 

    
        
        model.addAttribute("items", allCartItems);
        
        
        return "store/cart"; 
    }

}
