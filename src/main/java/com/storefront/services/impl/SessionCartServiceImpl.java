package com.storefront.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.storefront.entities.Cart;
import com.storefront.entities.CartItems;
import com.storefront.entities.Items;
import com.storefront.entities.User;
import com.storefront.helpers.SessionCart;
import com.storefront.services.ItemsService;
import com.storefront.services.SessionCartService;

import lombok.Getter;

@Service
public class SessionCartServiceImpl implements SessionCartService {

    @Autowired
    private ItemsService itemsService;

    @Getter
    private List<CartItems> cartItems = new ArrayList<>();

    @Override
    public String getCartId(SessionCart sessionCart) {
        return sessionCart.getCartId();
    }

    @Override
    public Cart getCart(SessionCart sessionCart) {
        return sessionCart.getCart();
    }

    @Override
    public void setCart(SessionCart sessionCart, Cart cart) {
        sessionCart.setCart(cart);
    }

    @Override
    public void addItem(SessionCart sessionCart, String itemId) {
        System.out.println("Adding item to cart: " + itemId); // Add logging

        Cart cart = sessionCart.getCart();
        List<CartItems> cartItems = cart.getCartItems();
        // check existing
        for (CartItems ci : cartItems) {
            if (ci.getItems().getItemId().equals(itemId)) {
                ci.setQuantity(ci.getQuantity() + 1);
                return;
            }
        }
        // add new
        Items item = itemsService.getById(itemId);
        if (item != null) {
            CartItems newCi = new CartItems();
            newCi.setCartItemId(UUID.randomUUID().toString());
            newCi.setCart(cart);
            newCi.setQuantity(1);
            newCi.setItems(item);
            cartItems.add(newCi);
        }
    }

    @Override
    public void removeItem(SessionCart sessionCart, String itemId) {
        Cart cart = sessionCart.getCart();
        List<CartItems> cartItems = cart.getCartItems();
        CartItems toRemove = null;
        for (CartItems ci : cartItems) {
            if (ci.getItems().getItemId().equals(itemId)) {
                toRemove = ci;
                break;
            }
        }
        if (toRemove != null) {
            cartItems.remove(toRemove);
            cart.getCartItems().remove(toRemove);
        }
    }

    @Override
    public int getItemCount(SessionCart sessionCart) {
        return sessionCart.getCart().getCartItems().size();
    }

    @Override
    public int getTotalItems(SessionCart sessionCart) {
        int total = 0;
        for (CartItems ci : sessionCart.getCart().getCartItems()) {
            total += ci.getQuantity();
        }
        return total;
    }

    @Override
    public List<CartItems> getAllCartItems(SessionCart sessionCart) {
        List<CartItems> items = sessionCart.getCart().getCartItems();
        // Update total price for each item before returning
        items.forEach(item -> {
            item.setTotalPrice(item.getItems().getPrice() * item.getQuantity());
            item.setPrice(item.getItems().getPrice());
        });
        return new ArrayList<>(items);
    }

    @Override
    public List<Items> getAllItemsOfCart(SessionCart sessionCart) {
        List<Items> itemsList = new ArrayList<>();
        for (CartItems ci : sessionCart.getCart().getCartItems()) {
            itemsList.add(ci.getItems());
        }
        return itemsList;
    }

    

    

    @Override
    public void clearCart(SessionCart sessionCart) {
        Cart cart = sessionCart.getCart();
        cart.getCartItems().clear();
    }

    @Override
    public Map<String, Integer> printCartItemsSummary(SessionCart sessionCart) {
        Map<String, Integer> summary = new HashMap<>();
        for (CartItems ci : sessionCart.getCart().getCartItems()) {
            summary.put(ci.getItems().getItemId(), ci.getQuantity());
        }
        return summary;
    }

    public int getTotalPrice(SessionCart sessionCart) {
        int total = 0;
        for (CartItems ci : sessionCart.getCart().getCartItems()) {
            total += ci.getItems().getPrice() * ci.getQuantity();
        }
        return total;
    }

    public void setUser(SessionCart sessionCart, User user) {
        sessionCart.getCart().setUser(user);
    }

    public User getUser(SessionCart sessionCart) {
        return sessionCart.getCart().getUser();
    }

    @Override
    public void updateItemQuantity(SessionCart sessionCart,String itemId, int quantity) {
        for (CartItems cartItem : cartItems) {
            if (cartItem.getItems().getItemId().equals(itemId)) {
                cartItem.setQuantity(quantity);
                System.out.println("Updated quantity for item " + itemId + " to " + quantity);
                return;
            }
        }
    }

    // @Override
    // public int getTotalPrice(SessionCart sessionCart) {
    //     int total = 0;
    //     for (CartItems item : cartItems) {
    //         total += item.getItems().getPrice() * item.getQuantity();
    //     }
    //     return total;
    // }
}