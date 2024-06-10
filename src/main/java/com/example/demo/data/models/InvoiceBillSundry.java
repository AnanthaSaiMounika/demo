package com.example.demo.data.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class InvoiceBillSundry {
    @Id
    private String id = UUID.randomUUID().toString();
    private String billSundryName;
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
    public String getBillSundryName() {
        return billSundryName;
    }
    public void setBillSundryName(String billSundryName) {
        this.billSundryName = billSundryName;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
