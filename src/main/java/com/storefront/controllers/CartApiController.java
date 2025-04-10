package com.storefront.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storefront.dto.ItemRequest;
import com.storefront.entities.CartItems;
import com.storefront.entities.Items;
import com.storefront.entities.User;
import com.storefront.helpers.Helper;
import com.storefront.helpers.SessionCart;
import com.storefront.services.CartItemsService;
import com.storefront.services.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    @PostMapping("/update")
    public ResponseEntity<?> addToCart(HttpSession session) {
        System.out.println("Processing Cart");
   
        // Try to get the cart from session
        SessionCart cart = (SessionCart) session.getAttribute("cart");

        if (cart != null) {
            System.out.println("Cart from session: " + cart.getCartId());
        } else {
            System.out.println("No cart found in session, creating a new one.");
            cart = new SessionCart();
            System.out.println("New cart created with ID: " + cart.getCartId());
            session.setAttribute("cart", cart);
        }

        return ResponseEntity.ok("Cart processed");
    }
}

// @RestController
// @RequestMapping("/api/cart")
// public class CartApiController {

//     @Autowired
//     private CartItemsService cartItemsService;

//     @Autowired
//     private UserService userService;

//     @PostMapping("/update")
//     public ResponseEntity<?> addToCart(@RequestBody String itemId, String action, HttpSession session) {
        
//         System.out.println("Processing Cart");

//         if (itemId != null) {
//         System.out.println("🧾 Received Item:");
//         System.out.println("➡️ Item ID: " + itemId);
//         } else {
//             System.out.println("❌ Item ID is null");
//         }
//         // System.out.println("➡️ Price: " + newItem.getPrice());
   
        
//     // Try to get the cart from session
//     SessionCart cart = (SessionCart) session.getAttribute("cart");

//     if (cart != null) {
//         System.out.println("Cart from session: " + cart.getCartId());
//     } else {
//         System.out.println("No cart found in session, creating a new one.");
//     }

//     System.out.println("Cart from session: " + cart);


//     // If no cart exists, create one and store it
//     if (cart == null) {
//         cart = new SessionCart();
//     }

//     System.out.println("Cart from session: " + cart);

//     // // Add the new item
//     // cart.addItem(newItem);

//     // Save back to session
//     session.setAttribute("cart", cart);

//     return ResponseEntity.ok("Item added to cart");
// }

// }

