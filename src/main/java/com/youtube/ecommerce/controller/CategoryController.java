package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.Repository.CategoryRepo;
import com.youtube.ecommerce.model.Category;
import com.youtube.ecommerce.model.Products;
import com.youtube.ecommerce.service.ProductService.ProductServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryRepo categoryRepo;
    
    @Autowired
    ProductServices productService;
    
    @RequestMapping("/cates")
    public List<Category> getAllCate() {
        System.out.println("Get all Products...");
        return productService.getAllCategory();
    }
    
    @PostMapping("/category")
    public Category createsProducts(@RequestBody Category category) {
        return categoryRepo.save(category);
    }

}
