package com.example.demo.data.models;

import java.util.List;

public class InvoiceResponse {
    private String id;
    private InvoiceHeader invoiceHeader;
    private List<Item> invoiceItems;
    private List<InvoiceBillSundry> invoiceBillSundryList;
    public List<InvoiceBillSundry> getInvoiceBillSundryList() {
        return invoiceBillSundryList;
    }
    public void setInvoiceBillSundryList(List<InvoiceBillSundry> invoiceBillSundryList) {
        this.invoiceBillSundryList = invoiceBillSundryList;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }
    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }
    public List<Item> getInvoiceItems() {
        return invoiceItems;
    }
    public void setInvoiceItems(List<Item> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}
