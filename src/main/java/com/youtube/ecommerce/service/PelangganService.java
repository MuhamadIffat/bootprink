/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.youtube.ecommerce.service;

import com.youtube.ecommerce.Exeption.UserNotFoundException;
import com.youtube.ecommerce.Repository.UserRepository;
import com.youtube.ecommerce.model.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
@Transactional
public class PelangganService {
    
    private UserRepository userRepository;
    
    @Autowired
    public PelangganService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User addUser(User user){
        return userRepository.save(user);
    }
    
    public List<User> findAllUser(){
        return userRepository.findAll();
    }
    
    public User updateUser(User user){
        return userRepository.save(user);
    }
    
    public User findUserById(Long id){
        return userRepository.findUserById(id)
                .orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
    }
    
    public void deleteUser(Long id){
        userRepository.deleteUserById(id);
    }
}
