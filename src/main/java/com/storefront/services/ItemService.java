 package com.storefront.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.storefront.entities.Items;

public interface ItemService {

    List<Items> getAllItems(); 

    Items getItemById(String id); 

    Page<Items> searchByName(String nameKeyword, int page, int size, String sortBy, String order);
    

}
