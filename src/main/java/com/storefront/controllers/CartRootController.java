package com.storefront.controllers;

import org.slf4j.LoggerFactory;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.storefront.entities.Cart;
import com.storefront.entities.User;
import com.storefront.helpers.CookieManager;
import com.storefront.helpers.Helper;
import com.storefront.helpers.SessionCart;
import com.storefront.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
@ControllerAdvice
public class CartRootController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired 
    private SessionCart sessionCart;

    @Autowired
    private CookieManager cookieManager;

    @ModelAttribute
    public void CurrentCartInformation(Model model, Authentication authentication, HttpServletRequest request) {
        // Guest user - load cart from cookies
        if (authentication == null) {
            if (sessionCart.getItemCount() == 0) {
                Map<String, Integer> savedCart = cookieManager.loadCartFromCookie(request);
                for (Map.Entry<String, Integer> entry : savedCart.entrySet()) {
                    // Add items from cookie to session cart
                    sessionCart.addItem(entry.getKey());
                    if (entry.getValue() > 1) {
                        sessionCart.updateItemQuantity(entry.getKey(), entry.getValue());
                    }
                }
            }
            
            // Add cart information to the model for all views
            model.addAttribute("cartItemCount", sessionCart.getItemCount());
            model.addAttribute("cartTotalItems", sessionCart.getTotalItems());
            model.addAttribute("cartTotalPrice", sessionCart.getTotalPrice());
            return;
        }
        
        // // Authenticated user - can load cart from database if needed
        // String username = authentication.getName();
        // logger.info("Loading cart for authenticated user: {}", username);
        
        // // If cart is empty, try to load from user's saved cart in database
        // if (sessionCart.getItemCount() == 0) {
        //     try {
        //         // Assuming userService has a method to get user's cart from the database
        //         Map<String, Integer> userCart = userService.getUserCart(username);
                
        //         // Populate session cart with items from database
        //         for (Map.Entry<String, Integer> entry : userCart.entrySet()) {
        //             sessionCart.addItem(entry.getKey());
        //             if (entry.getValue() > 1) {
        //                 sessionCart.updateItemQuantity(entry.getKey(), entry.getValue());
        //             }
        //         }
        //         logger.info("Loaded {} items from database for user {}", sessionCart.getItemCount(), username);
        //     } catch (Exception e) {
        //         logger.error("Error loading cart from database: {}", e.getMessage());
        //     }
        // }
        
        // // Add cart information to the model for all views
        // model.addAttribute("cartItemCount", sessionCart.getItemCount());
        // model.addAttribute("cartTotalItems", sessionCart.getTotalItems());
        // model.addAttribute("cartTotalPrice", sessionCart.getTotalPrice());
    }
}