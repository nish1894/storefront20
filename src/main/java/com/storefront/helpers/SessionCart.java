package com.storefront.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.storefront.entities.Cart;
import com.storefront.entities.CartItems;
import com.storefront. entities.User;


public class SessionCart implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String cartId;
    
    public SessionCart() {
        this.cartId = UUID.randomUUID().toString();
    }
    
    public String getCartId() {
        return cartId;
    }
    
    @Override
    public String toString() {
        return "SessionCart{cartId='" + cartId + "'}";
    }
}

// public class SessionCart {
//     // private List<CartItems> cartItems = new ArrayList<>();
    

//     private Cart cart;

//     public SessionCart() {
//         this.cart = new Cart();
//         cart.setCartId(UUID.randomUUID().toString());
//         cart.setUpdatedOn(java.time.LocalDateTime.now());
//         cart.setItemCount(0);
//         cart.setTotalPrice(0);
//         cart.setCartItems(new ArrayList<>());
//         cart.setUser(new User());
//     }

//     public Cart cart() {
//         return this.cart;
//     }

//     public String getCartId() {
//         return this.cart().getCartId();
//     }

 


  

//     // public List<CartItems> getItems() {
//     //     return cartItems;
//     // }

//     // public void addItem(CartItems item) {
//     //     this.cartItems.add(item);

//     //     // Check if the item already exists in the cart
//     //     boolean itemExists = false;
//     //     for (CartItems cartItem : cartItems) {
//     //         if (cartItem.getItems().getItemId().equals(item.getItems().getItemId())) {
//     //             itemExists = true;
//     //             break;
//     //         }
//     //     }
//     //     // If the item already exists, update its quantity and total price

      
//     //     if (itemExists) {
//     //         for (CartItems cartItem : cartItems) {
//     //             if (cartItem.getItems() == null) {
//     //                 throw new IllegalArgumentException("CartItem must have an associated Item before adding to cart.");
//     //             }
        
//     //             if (cartItem.getItems().getItemId().equals(item.getItems().getItemId())) {
//     //                 cartItem.setQuantity(cartItem.getQuantity() + 1);
//     //                 cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getPrice());

//     //             }
//     //         }
//     //     } else {
//     //     // If the item does not exist, add it to the cart
//     //     item.setQuantity(1); // Set initial quantity to 1
//     //     item.setTotalPrice(item.getQuantity() * item.getPrice());
//     //     cartItems.add(item);
//     //     }

//     //     // update the quantity of the item in the cart
//     //     cart.setItemCount(cart.getItemCount() + 1);
//     //     // update the total price of the item in the cart
//     //     cart.setTotalPrice(cart.getTotalPrice() + item.getPrice()); 


//     //     // update the updatedOn field of the cart
//     //     cart.setUpdatedOn(java.time.LocalDateTime.now());

//     //     //update the inventoru in itmems  
        
//     // }

//     // public void removeItem(CartItems item) {
//     //    // Check if the item already exists in the cart
//     //    boolean itemExists = false;
//     //    for (CartItems cartItem : cartItems) {
//     //        if (cartItem.getItems().getItemId().equals(item.getItems().getItemId())) {
//     //            itemExists = true;
//     //            break;
//     //        }
//     //    }
//     //    // If the item already exists, update its quantity and total price
//     //    if (itemExists) {
//     //        for (CartItems cartItem : cartItems) {
//     //            if (cartItem.getItems().getItemId().equals(item.getItems().getItemId())) {
//     //                cartItem.setQuantity(cartItem.getQuantity() + 1);
//     //                cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getPrice());

//     //            }
//     //        }
//     //    } else {
//     //    // If the item does not exist, add it to the cart
//     //    throw new ResourceNotFoundException("Item not found in the cart  ");
       
//     //    }
//     //    // Update the total price of the cart
//     //    float totalPrice = 0;
//     //    for (CartItems cartItem : cartItems) {
//     //        totalPrice += cartItem.getTotalPrice();
//     //    }
//     //    cart.setTotalPrice(totalPrice);    
//     // }

    
// }

