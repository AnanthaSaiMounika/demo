package com.example.demo.data.models;

import java.util.List;

public class InvoiceReq {
    
    private List<Item> invoiceItems;
    private InvoiceHeader header;
    private InvoiceBillSundry billSundry;
    public List<Item> getInvoiceItems() {
        return invoiceItems;
    }
    public void setInvoiceItems(List<Item> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
    public InvoiceHeader getHeader() {
        return header;
    }
    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }
    public InvoiceBillSundry getBillSundry() {
        return billSundry;
    }
    public void setBillSundry(InvoiceBillSundry billSundry) {
        this.billSundry = billSundry;
    }
}
