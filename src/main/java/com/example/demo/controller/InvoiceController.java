package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.models.InvoiceHeader;
import com.example.demo.data.models.InvoiceReq;
import com.example.demo.data.models.InvoiceResponse;
import com.example.demo.service.InvoiceService;

@RestController
@RequestMapping(path = "/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("")
    public Map<String, Object> createInvoice(@RequestBody InvoiceReq request) {
        try {
            InvoiceHeader response = invoiceService.createInvoice(request);
            Map<String, Object> finalResponse = new HashMap<>();
            finalResponse.put("header", response);
            finalResponse.put("success", Boolean.TRUE);
            return finalResponse;
        } catch (Exception e) {
            Map<String, Object> finalResponse = new HashMap<>();
            finalResponse.put("success", Boolean.FALSE);
            finalResponse.put("message", e.getMessage());
            return finalResponse;
        }
    }

    @DeleteMapping("/{id}")
    public Boolean deleteInvoice(@PathVariable(name = "id", required = true) String headerId) {
        return invoiceService.deleteInvoice(headerId);
    }

    @GetMapping("")
    public List<InvoiceResponse> getAllInvoices() {
        return invoiceService.getAllInvoiceResponses();
    }

    @GetMapping("{id}")
    public InvoiceResponse getInvoiceById(@PathVariable(name = "id", required = true) String headerId) {
        return invoiceService.getInvoiceById(headerId);
    }
    
}
