package com.storefront.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.storefront.entities.User;
import com.storefront.helpers.ResourceNotFoundException;
import com.storefront.repositories.UserRepo;
import com.storefront.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo; 



    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(user.getPassword());

        user.setName(user.getFirstName()+ " "+ user.getLastName());

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String Id) {
        return userRepo.findById(Id); 
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found")); 

        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        user2.setEmail(user.getEmail());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setDOB(user.getDOB());
        user2.setPassword(user.getPassword());
        user2.setEmailVerified(user.getEmailVerified());
        user2.setPhoneVerified(user.getPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserID(user.getProviderUserID());

        user2.setName(user.getFirstName()+ " "+ user.getLastName());

        User save = userRepo.save(user2);
        return Optional.ofNullable(save); 
    }

    @Override
    public void deleteUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        userRepo.delete(user2); 
          
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user2 = userRepo.findByEmail(email).orElse(null); 
        return user2 != null ? true:false; 
    }

    @Override
    public boolean isUserExistByPhoneNumber(String phoneNumber) {
        User user2 = userRepo.findByPhoneNumber(phoneNumber).orElse(null);
        return user2 != null ? true:false; 
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll(); 
    }

    @Override
    public User getUserByEmail(String email) {
       return  userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

}
 