package com.storefront.entities;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItems {

    @Id
    private String cartItemId; // You can generate this as cartId_itemId or use UUID

    private Integer quantity;
    private float price;
    private float totalPrice;
    private String updatedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Items items;

    // Getters and setters...

    // // Optional: Generate ID like cartId_itemId
    // public void generateId() {
    //     if (cart != null && items != null) {
    //         this.id = cart.getId() + "_" + items.getItemId();
    //     }
    // }
}
