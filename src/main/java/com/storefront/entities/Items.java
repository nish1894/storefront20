package com.storefront.entities;

import jakarta.persistence.ForeignKey;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Items {

    @Id
    private String itemId;

    private String title;
    private float price;

    @Column(length = 1000)
    private String description;
    private String image;
    private int inventory;
    private float rating;
    private int ratingCount;


@ManyToOne
@JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category"))
@OnDelete(action = OnDeleteAction.SET_NULL)
private Categories category;

}
