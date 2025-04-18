package com.storefront.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.storefront.entities.Categories;
import com.storefront.entities.Items;


@Repository
public interface ItemsRepo extends JpaRepository<Items, String> {

    //find the contact by user 

    Page<Items> findByCategory(Categories category, Pageable pageable);

    Page<Items> findByTitleContaining( String namekeyword, Pageable pageable);




    
    

}
