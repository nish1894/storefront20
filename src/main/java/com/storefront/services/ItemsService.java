 package com.storefront.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.storefront.entities.Items;
import com.storefront.entities.User;

public interface ItemsService {

     // save items
     Items save(Items items);

     // update items
     Items update(Items items);
 
     // get items
     List<Items> getAll();
 
     // get item by id
     Items getById(String id);
 
     // delete items
     void delete(String id);
 
     // search items
    Page<Items> searchByTitle(String nameKeyword, int size, int page, String sortBy, String order);
  
    // Page<Items> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);
  
 
    //  // get items by category
    //  List<Items> getByCategory(String userId);
 
     // get contacts by category
     Page<Items> getByCategory(User user, int page, int size, String sortField, String sortDirection);
  

}
