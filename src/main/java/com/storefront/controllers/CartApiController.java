package com.storefront.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.storefront.dto.ItemRequest;
import com.storefront.helpers.CookieManager;
import com.storefront.helpers.SessionCart;
import com.storefront.services.CartService;
import com.storefront.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CartService cartService;
    
    @Autowired
    private SessionCart sessionCart;

    @Autowired
    private CookieManager cookieManager;
    
    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public ResponseEntity<?> updateCart(
            @RequestBody ItemRequest item, 
            HttpServletResponse response,
            HttpServletRequest request,
            Authentication authentication) {
            
        logger.info("Processing cart update: Item ID: {} (action: {})", item.getItemId(), item.getAction());
        
        // Process the current action
        if (item.getAction().equals("add")) {
            sessionCart.addItem(item.getItemId());
        } else if (item.getAction().equals("remove")) {
            sessionCart.removeItem(item.getItemId());
        // } else if (item.getAction().equals("update") && item.getQuantity() != null) {
        //     sessionCart.updateItemQuantity(item.getItemId(), item.getQuantity());
        } else if (item.getAction().equals("clear")) {
            sessionCart.clearCart();
            cookieManager.deleteCookie(response);
            return ResponseEntity.ok(Map.of(
                "cartId", sessionCart.getCartId(),
                "itemCount", 0,
                "totalItems", 0,
                "totalPrice", 0.0
            ));
        }

        // Save current cart state after modification
        Map<String, Integer> cartSummary = sessionCart.printCartItemsSummary();
        
        // For guest users, save to cookie
        if (authentication == null) {
            cookieManager.saveCartToCookie(response, cartSummary);
        } 
        // // For authenticated users, save to database
        // else {
        //     String username = authentication.getName();
        //     userService.saveUserCart(username, cartSummary);
        //     logger.info("Saved cart to database for user: {}", username);
        // }

        return ResponseEntity.ok(Map.of(
            "cartId", sessionCart.getCartId(),
            "itemCount", sessionCart.getItemCount(),
            "totalItems", sessionCart.getTotalItems(),
            "totalPrice", sessionCart.getTotalPrice()
        ));
    }
    
    @GetMapping
    public ResponseEntity<?> getCartContents() {
        // No need to load cart data here as it's handled by CartRootController
        // Just return current cart state
        return ResponseEntity.ok(Map.of(
            "cartId", sessionCart.getCartId(),
            "items", sessionCart.getAllCartItems(),
            "itemCount", sessionCart.getItemCount(),
            "totalItems", sessionCart.getTotalItems(),
            "totalPrice", sessionCart.getTotalPrice()
        ));
    }
    
}

// package com.storefront.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.storefront.dto.ItemRequest;
// import com.storefront.helpers.CookieManager;
// import com.storefront.helpers.SessionCart;
// import com.storefront.services.CartService;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/cart")
// public class CartApiController {

//     @Autowired
//     private CartService cartService;
    
//     @Autowired
//     private SessionCart sessionCart;

//     @Autowired
//     private CookieManager cookieManager;

//     @PostMapping("/update")
//     public ResponseEntity<?> addToCart(
//             @RequestBody ItemRequest item, 
//             HttpSession session,
//             HttpServletResponse response,  // Add this for cookie management
//             HttpServletRequest request) {  // Add this to read cookies
            
//         System.out.println("Processing Cart");
//         System.out.println("➡️ Item ID: " + item.getItemId() + " (action: " + item.getAction() + ")");

//         // If cart is empty, try to load from cookies first
//         if (sessionCart.getItemCount() == 0) {
//             Map<String, Integer> savedCart = cookieManager.loadCartFromCookie(request);
//             for (Map.Entry<String, Integer> entry : savedCart.entrySet()) {
//                 // Add items from cookie to session cart
//                 sessionCart.addItem(entry.getKey());
//                 if (entry.getValue() > 1) {
//                     sessionCart.updateItemQuantity(entry.getKey(), entry.getValue());
//                 }
//             }
//         }

//         // Process the current action
//         if (item.getAction().equals("add")) {
//             sessionCart.addItem(item.getItemId());
//         } else if (item.getAction().equals("remove")) {
//             sessionCart.removeItem(item.getItemId());
//         } else if (item.getAction().equals("clear")) {
//             sessionCart.clearCart();
//             cookieManager.deleteCookie(response);
//             return ResponseEntity.ok(Map.of(
//                 "cartId", sessionCart.getCartId(),
//                 "itemCount", 0
//             ));
//         }

//         sessionCart.getItemCount(); 
//         Map<String, Integer> cartSummary = sessionCart.printCartItemsSummary();
        
//         // Save current cart state to cookie after any modification
//         cookieManager.saveCartToCookie(response, cartSummary);

//         return ResponseEntity.ok(Map.of(
//             "cartId", sessionCart.getCartId(),
//             "itemCount", sessionCart.getItemCount()
//         ));
//     }
    
//     // Optional method to get cart contents - also load from cookie if empty
//     @GetMapping
//     public ResponseEntity<?> getCartContents(HttpServletRequest request) {
//         // If cart is empty, try to load from cookies
//         if (sessionCart.getItemCount() == 0) {
//             Map<String, Integer> savedCart = cookieManager.loadCartFromCookie(request);
//             for (Map.Entry<String, Integer> entry : savedCart.entrySet()) {
//                 sessionCart.addItem(entry.getKey());
//                 if (entry.getValue() > 1) {
//                     sessionCart.updateItemQuantity(entry.getKey(), entry.getValue());
//                 }
//             }
//         }
        
//         return ResponseEntity.ok(Map.of(
//             "cartId", sessionCart.getCartId(),
//             "items", sessionCart.getAllCartItems(),
//             "itemCount", sessionCart.getItemCount(),
//             "totalItems", sessionCart.getTotalItems(),
//             "totalPrice", sessionCart.getTotalPrice()
//         ));
//     }
// }



// // @RestController
// // @RequestMapping("/api/cart")
// // public class CartApiController {

// //     @Autowired
// //     private CartService cartService;
    
// //     @Autowired
// //     private SessionCart sessionCart; // Let Spring inject the managed bean

// //     @Autowired
// //     private CookieManager cookieManager;





// //     @PostMapping("/update")
// //     public ResponseEntity<?> addToCart(@RequestBody ItemRequest item, HttpSession session) {
// //         System.out.println("Processing Cart");
// //         System.out.println("➡️ Item ID: " + item.getItemId() + " (action: " + item.getAction() + ")");
// //         // System.out.println("➡️ action: " + item.getAction());

// //         // No need to get cart from session or create it manually
// //         // Spring will inject and manage the session-scoped bean for us

// //         if (item.getAction().equals("add")) {
// //             sessionCart.addItem(item.getItemId());
// //         }
// //         // } else if (item.getAction().equals("remove")) {
// //         //     sessionCart.removeItem(item.getItemId());
// //         // } else if (item.getAction().equals("update") && item.getQuantity() != null) {
// //         //     sessionCart.updateItemQuantity(item.getItemId(), item.getQuantity());
// //         // }

// //         sessionCart.getItemCount(); 
// //         sessionCart.printCartItemsSummary(); 
        

// //         return ResponseEntity.ok(Map.of(
// //             "cartId", sessionCart.getCartId(),
// //             "itemCount", sessionCart.getItemCount()
// //         ));
// //     }
    

// //     // Optional method to get cart contents
// //     @GetMapping
// //     public ResponseEntity<?> getCartContents() {
// //         return ResponseEntity.ok(sessionCart.getCart());
// //     }
// // }
