package com.youtube.ecommerce.service.ProductService;

import com.youtube.ecommerce.Exeption.UserNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.ecommerce.Repository.CategoryRepo;
import com.youtube.ecommerce.Repository.ProductRepo;
import com.youtube.ecommerce.dto.ListProduct;
import com.youtube.ecommerce.model.Category;
import com.youtube.ecommerce.model.Products;
import javax.transaction.Transactional;

@Service
@Transactional
public class ProductServices {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo cateRepo;

    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Products> getProductsByCategory(String product_id) {
        return productRepo.getByCategoryId(product_id);
    }

    public List<Category> getAllCategory() {
        return cateRepo.findAll();
    }

    public Products getProductsById(long productId) {
        return productRepo.findById(productId).orElseThrow(() -> new UserNotFoundException("Product is not found"));
    }

    public Products addProducts(Products products) {
        return productRepo.save(products);
    }

    public Products updateProducts(Products products) {
        return productRepo.save(products);
    }

    public void deleteProducts(Long id) {
        productRepo.deleteProductById(id);
    }

//    public List<Prod> getAll() {
//        return productRepo.findAll();
//    }

//    public long save(Products products) {
//        Products p = new Products();
//        p.setId(products.getId());
//        p.setName(products.getName());
//        p.setPrice(products.getPrice());
//        p.setId_category(products.getId_category());
//        p.setFileName(products.getFileName());
//        p.setStock(products.getStock());
//        return productRepo.save(p).getId();
//    }

    public Products findById(long productId) {
        return productRepo.findById(productId).orElseThrow(() -> new UserNotFoundException("Product is not found"));
    }

}
