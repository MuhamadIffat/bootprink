package com.youtube.ecommerce.dto;

import java.util.Date;

public class ListPenjualan {

    private long id;
    private String order_id;
    private String payment_type;
    private String delivery_address;
    private long user_id;
    private int qty;
    private double price;
    private Date createdAt;

    public ListPenjualan(long id, String order_id, String payment_type, String delivery_address,long user_id,int qty, double price, Date createdAt) {
        this.id = id;
        this.order_id = order_id;
        this.payment_type = payment_type;
        this.delivery_address = delivery_address;
        this.user_id = user_id;
        this.qty = qty;
        this.price = price;
        this.createdAt = createdAt;
    }

    
    public ListPenjualan() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    

    
}
