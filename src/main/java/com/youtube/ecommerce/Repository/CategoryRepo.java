package com.youtube.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtube.ecommerce.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo  extends JpaRepository<Category, Long> {
    
}
