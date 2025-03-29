package com.storefront.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OrderItems {

    @Id
    private String orderItemId;

    private String itemsId;
    private String quantity;
    private String orderId; 


}
