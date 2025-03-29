package com.storefront.entities;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;

@Entity
public class CartItems {

    @Id
    private String cartId;

    private String itemId;
    private String quantity; 

   
    private String totalPrice;

    private String updatedOn; 

    private String userId; 

}
