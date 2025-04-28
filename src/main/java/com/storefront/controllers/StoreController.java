package com.storefront.controllers;

import java.util.List;
import java.util.Map;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storefront.dto.CartItemDTO;
import com.storefront.entities.CartItems;
import com.storefront.entities.Items;
import com.storefront.helpers.SessionCart;
import com.storefront.services.ItemsService;
import com.storefront.services.SessionCartService;

@Controller
@RequestMapping("/store")
public class StoreController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private SessionCart sessionCart; // Let Spring inject the managed bean

    @Autowired
    private SessionCartService sessionCartService;

    

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

        // List<Items> allCartItems = sessionCartService.getAllItemsOfCart(sessionCart);
        
        List<CartItems> allCartItems1 = sessionCartService.getAllCartItems(sessionCart);

        List<CartItemDTO> cartItemDTOs = CartItemDTO.fromCartItems(allCartItems1);



         // Print items to terminal
        // logger.info("All cart Items retrieved: {}", cartItemDTOs);
        // logger.info("All cart Items retrieved: {}", allCartItems);


        // Map<String, Integer> cartSummary = sessionCartService.printCartItemsSummary(sessionCart);

        // logger.info("CART SUMMARY: {}", cartSummary);



        // List<Items> allItems = itemsService.getAll(); 


        
        model.addAttribute("items", cartItemDTOs);

        
        
        return "store/cart"; 
    }

    @RequestMapping("/product/{id}")
    public String product(@PathVariable("id") String id, Model model) {
        // Fetch product by id from your service/repository
        Items product = itemsService.getById(id);
        
        // Add product to model
        model.addAttribute("product", product);
        
        return "store/product";
    }


}
