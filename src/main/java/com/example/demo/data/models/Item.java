package com.example.demo.data.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {
    
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private int quantity;
    private Long price;
    private Long amount;
    private String headerId;
    public String getHeaderId() {
        return headerId;
    }
    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
