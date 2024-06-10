package com.example.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.models.InvoiceHeader;

public interface InvoiceHeaderRepository extends JpaRepository<InvoiceHeader, String>{
    
}
