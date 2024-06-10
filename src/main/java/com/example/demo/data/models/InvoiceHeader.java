package com.example.demo.data.models;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class InvoiceHeader {
    
    @Id
    private String id = UUID.randomUUID().toString();
    @CreatedDate
    private Date createdDate;
    private int invoiceNumber;
    private String customerName;
    private String billingAddress;
    private String shippingAddress;
    private String gstIN;
    private Long amount;
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id=id;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public String getGstIN() {
        return gstIN;
    }
    public void setGstIN(String gstIN) {
        this.gstIN = gstIN;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
