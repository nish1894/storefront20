package com.storefront.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.storefront.entities.Items;
import com.storefront.entities.User;
import com.storefront.helpers.ResourceNotFoundException;
import com.storefront.repositories.ItemsRepo;
import com.storefront.services.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsRepo itemsRepo;

    @Override
    public Items save(Items items) {
        
        return itemsRepo.save(items); 
    }

    @Override
    public Items update(Items items) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Items> getAll() {
        return itemsRepo.findAll();
    }

    @Override
    public Items getById(String id) {

        return itemsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        
    }

    @Override
    public void delete(String id) {
        var contact = itemsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id"));

        itemsRepo.delete(contact);
    }

    @Override
    public Page<Items> searchByTitle(String nameKeyword, int size, int page, String sortBy, String order) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
         var pageable = PageRequest.of(page, size, sort);
         return itemsRepo.findByTitleContaining( nameKeyword, pageable);
    }

    @Override
    public Page<Items> getByCategory(User user, int page, int size, String sortField, String sortDirection) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByCategory'");
    }

}
