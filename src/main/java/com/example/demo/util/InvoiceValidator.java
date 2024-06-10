package com.example.demo.util;

import org.springframework.stereotype.Component;

import com.example.demo.data.models.InvoiceReq;
import com.example.demo.data.models.Item;

@Component
public class InvoiceValidator {
    
    public Boolean validateInvoicePayload(InvoiceReq request) throws Exception {
        Long totalAllItemsAmount = Long.valueOf(0);
        if (request.getInvoiceItems() != null && !request.getInvoiceItems().isEmpty()) {
            for (Item invoiceItem: request.getInvoiceItems()) {
                if (invoiceItem.getQuantity() <= 0) {
                    throw new Exception("Quantity must be greater than 0");
                }
                if (invoiceItem.getPrice() <= 0) {
                    throw new Exception("Price must be greater than 0");
                }
                if (invoiceItem.getAmount() <= 0) {
                    throw new Exception("Total amount must be greater than 0");
                }
                Long totalAmount = invoiceItem.getQuantity() * invoiceItem.getPrice();
                Long givenAmount = invoiceItem.getAmount();
                totalAllItemsAmount = totalAllItemsAmount + totalAmount;
                if (totalAmount != givenAmount) {
                    throw new Exception("Given amount is appropriate with purchased items");
                }
            }
        }
        if (request.getHeader().getAmount() != (totalAllItemsAmount + request.getBillSundry().getAmount())) {
            throw new Exception("Given amount is not appropriate with purchased items and billSundry");
        }
        Long minValue = Long.MIN_VALUE;
        Long maxValue = Long.MAX_VALUE;
        Long billSundryAmount = request.getBillSundry().getAmount();
        if (!(billSundryAmount >= minValue && billSundryAmount <= maxValue)) {
            throw new Exception("Bill Sundry amount is not in valid range");
        }
        return Boolean.TRUE;
    }
}
