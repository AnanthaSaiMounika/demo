package com.example.demo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.data.models.InvoiceBillSundry;

public interface InvoiceBillSundryRepository extends JpaRepository<InvoiceBillSundry, String>{
    
    @Query("SELECT c from InvoiceBillSundry c WHERE c.headerId = :id")
    List<InvoiceBillSundry> findAllBillSundryByHeaderId(String id);
}
