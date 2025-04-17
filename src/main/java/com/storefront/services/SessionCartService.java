package com.storefront.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.storefront.entities.Cart;
import com.storefront.entities.CartItems;
import com.storefront.entities.Items;
import com.storefront.entities.User;
import com.storefront.helpers.SessionCart;

import lombok.Getter;

public interface SessionCartService {


    String getCartId(SessionCart sessionCart); 
    
    Cart getCart(SessionCart sessionCart);
    
    void setCart(SessionCart sessionCart, Cart cart);
    void addItem(SessionCart sessionCart, String itemId);
    void removeItem(SessionCart sessionCart, String itemId);
    // void updateItemQuantity(SessionCart sessionCart, String itemId, int quantity);
    int getItemCount(SessionCart sessionCart);
    int getTotalItems(SessionCart sessionCart);
    List<CartItems> getAllCartItems(SessionCart sessionCart);
    List<Items> getAllItemsOfCart(SessionCart sessionCart);
    void clearCart(SessionCart sessionCart);
    Map<String, Integer> printCartItemsSummary(SessionCart sessionCart);
    // void setUser(SessionCart sessionCart, User user);
    // User getUser(SessionCart sessionCart);

}
