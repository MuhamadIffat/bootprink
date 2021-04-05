package com.youtube.ecommerce.converter;

import com.youtube.ecommerce.dto.ListPenjualan;
import com.youtube.ecommerce.model.CheckoutCart;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public ListPenjualan entityToDto(CheckoutCart checkoutCart){
        ListPenjualan lp = new ListPenjualan();
        lp.setId(checkoutCart.getId());
        lp.setOrder_id(checkoutCart.getOrder_id());
        lp.setPayment_type(checkoutCart.getPayment_type());
        lp.setDelivery_address(checkoutCart.getDelivery_address());
        lp.setUser_id(checkoutCart.getUser_id());
        lp.setQty(checkoutCart.getQty());
        lp.setPrice(checkoutCart.getPrice());
        lp.setCreatedAt(checkoutCart.getCreatedAt());
        return lp;
    }
    public CheckoutCart dtoToEntity(ListPenjualan listPenjualan){
        CheckoutCart cc = new CheckoutCart();
        cc.setId(listPenjualan.getId());
        cc.setOrderId(listPenjualan.getOrder_id());
        cc.setPayment_type(listPenjualan.getPayment_type());
        cc.setDelivery_address(listPenjualan.getDelivery_address());
        cc.setUser_id(listPenjualan.getUser_id());
        cc.setQty(listPenjualan.getQty());
        cc.setPrice(listPenjualan.getPrice());
        cc.setCreatedAt(listPenjualan.getCreatedAt());
        return cc;
    }
    
    public List<ListPenjualan> entityToDto(List<CheckoutCart> checkoutCarts){
        return checkoutCarts.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
    
    public List<CheckoutCart> dtoToEntity(List<ListPenjualan> listPenjualans){
        return listPenjualans.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}

