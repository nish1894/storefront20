package com.storefront.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderItems {

    @Id
    private String orderItemId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Orders order;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "items_id")
    private Items items;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private float priceAtPurchase;

}