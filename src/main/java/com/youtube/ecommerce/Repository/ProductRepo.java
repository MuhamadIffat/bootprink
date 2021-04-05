package com.youtube.ecommerce.Repository;

import com.youtube.ecommerce.dto.ListProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.youtube.ecommerce.model.Products;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
@Transactional
public interface ProductRepo extends JpaRepository<Products, Long> {

    @Query("SELECT t FROM Products t where t.id_category = :code")
    public List<Products> findByCateg(@Param("code") String code);

    @Query("Select pro FROM Products pro WHERE pro.id_category=:cat_id")
    List<Products> getByCategoryId(@Param("cat_id") String cat_id);

    public Optional<Products> findById(Long id);

    void deleteProductById(Long id);
    
//    @Query(value = "SELECT new com.youtube.ecommerce.dto.ListProduct (pro.id,cat.id,pro.name,pro.price,pro.stock)"
//            +"FROM Products pro, Category cat WHERE pro.id_category = cat.id")
//    List<ListProduct> listProduct();
    
    @Query(value = "SELECT count(*) FROM Products WHERE id_category=:id")
    public int total(@Param("id")String id);
    
    @Query(value = "SELECT max(id) FROM Products WHERE id_category=:id")
    public int max(@Param("id")String id);
}
