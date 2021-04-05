package com.youtube.ecommerce.Repository;

import com.youtube.ecommerce.dto.ListPenjualan;
import com.youtube.ecommerce.model.CheckoutCart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<CheckoutCart, Long>{
    
}
