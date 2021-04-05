package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.Exeption.ResourceNotFoundException;
import com.youtube.ecommerce.Repository.UserRepository;
import com.youtube.ecommerce.model.User;
import com.youtube.ecommerce.service.PelangganService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")

public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PelangganService pelangganService;
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser () {
        List<User> users = pelangganService.findAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById (@PathVariable("id") Long id) {
        User user = pelangganService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = pelangganService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = pelangganService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        pelangganService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
