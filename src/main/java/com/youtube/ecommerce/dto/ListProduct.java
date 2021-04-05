package com.youtube.ecommerce.dto;

public class ListProduct {
    private Long id;
    
    private Long id_category;
    private String name;
    private int price;
    private int stock;

    public ListProduct() {
    }

    public ListProduct(Long id, Long id_category, String name, int price, int stock) {
        this.id = id;
        this.id_category = id_category;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
}
