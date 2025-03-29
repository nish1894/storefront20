package com.storefront.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Orders {

    @Id
    private String orderId;
    private String userId;
    private String totalPrice;
    private String status; 
    private String createdOn; 
    private String lastUpdate;

}
