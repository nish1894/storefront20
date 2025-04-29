package com.storefront.controllers;

import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
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
import com.storefront.services.SessionCartService;
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
    private SessionCartService sessionCartService;

    @Autowired
    private CookieManager cookieManager;

    @ModelAttribute
    public void CurrentCartInformation(Model model, Authentication authentication, HttpServletRequest request) {
        // Guest user - load cart from cookies
        if (authentication == null) {
            if (sessionCartService.getItemCount(sessionCart) == 0) {
                Map<String, Integer> savedCart = cookieManager.loadCartFromCookie(request);
                for (Map.Entry<String, Integer> entry : savedCart.entrySet()) {
                    // Add items from cookie to session cart
                    sessionCartService.addItem(sessionCart,entry.getKey());
                    if (entry.getValue() > 1) {
                        sessionCartService.updateItemQuantity(sessionCart,entry.getKey(), entry.getValue());
                    }
                }
            }

            float cartTotalPrice = sessionCartService.getTotalPrice(sessionCart);

            
            // Add cart information to the model for all views
            model.addAttribute("cartItemCount", sessionCartService.getItemCount(sessionCart));
            model.addAttribute("cartTotalItems", sessionCartService.getTotalItems(sessionCart));
            model.addAttribute("cartTotalPrice", cartTotalPrice);

            // Perform calculations
            DecimalFormat df = new DecimalFormat("0.00");
            float originalPrice = Float.parseFloat(df.format(1.30f * cartTotalPrice));
            float savings = Float.parseFloat(df.format(0.46f * cartTotalPrice));
            float tax = Float.parseFloat(df.format(0.53f * cartTotalPrice));

            // Add to model
            model.addAttribute("originalPrice", originalPrice);
            model.addAttribute("savings", savings);
            model.addAttribute("tax", tax);



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