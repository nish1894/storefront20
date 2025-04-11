package com.storefront.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.storefront.dto.ItemRequest;
import com.storefront.helpers.SessionCart;
import com.storefront.services.CartService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private SessionCart sessionCart; // Let Spring inject the managed bean

    @PostMapping("/update")
    public ResponseEntity<?> addToCart(@RequestBody ItemRequest item, HttpSession session) {
        System.out.println("Processing Cart");
        System.out.println("➡️ Item ID: " + item.getItemId());
        System.out.println("➡️ action: " + item.getAction());

        // No need to get cart from session or create it manually
        // Spring will inject and manage the session-scoped bean for us

        if (item.getAction().equals("add")) {
            sessionCart.addItem(item.getItemId());
        }
        // } else if (item.getAction().equals("remove")) {
        //     sessionCart.removeItem(item.getItemId());
        // } else if (item.getAction().equals("update") && item.getQuantity() != null) {
        //     sessionCart.updateItemQuantity(item.getItemId(), item.getQuantity());
        // }

        sessionCart.printCartItemsSummary(); 
        

        return ResponseEntity.ok(Map.of(
            "cartId", sessionCart.getCartId(),
            "itemCount", sessionCart.getItemCount()
        ));
    }
    

    // Optional method to get cart contents
    @GetMapping
    public ResponseEntity<?> getCartContents() {
        return ResponseEntity.ok(sessionCart.getCart());
    }
}


// @RestController
// @RequestMapping("/api/cart")
// public class CartApiController {

//     @Autowired
//     CartService cartService;

//     @PostMapping("/update")
//     public ResponseEntity<?> addToCart(@RequestBody ItemRequest item,HttpSession session) {
//         System.out.println("Processing Cart");
   
//         System.out.println("➡️ Item ID: " + item.getItemId());
//         System.out.println("➡️ action: " + item.getAction());

//         // Try to get the cart from session
//         SessionCart cart = (SessionCart) session.getAttribute("cart");

        

//         if (cart != null) {
//             System.out.println("Cart from session: " + cart.getCartId());
//         } else {
//             System.out.println("No cart found in session, creating a new one.");
//             cart = new SessionCart();

//             System.out.println("New cart created with ID: " + cart.getCartId());
//             session.setAttribute("cart", cart);
//         }

//         if(item.getAction().equals("add") ){
//             cart.addItem( item.getItemId());
//         }

//         System.out.println("➡️ cartItem: " + item.getAction());





//         return ResponseEntity.ok("Cart processed");
//     }
// }

