package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.models.InvoiceBillSundry;
import com.example.demo.data.models.InvoiceHeader;
import com.example.demo.data.models.InvoiceReq;
import com.example.demo.data.models.InvoiceResponse;
import com.example.demo.data.models.Item;
import com.example.demo.data.repository.InvoiceBillSundryRepository;
import com.example.demo.data.repository.InvoiceHeaderRepository;
import com.example.demo.data.repository.ItemRepository;
import com.example.demo.util.InvoiceValidator;

@Service
public class InvoiceService {

    @Autowired
    InvoiceValidator validator;

    @Autowired
    private InvoiceHeaderRepository headerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InvoiceBillSundryRepository billSundryRepository;
    
    public InvoiceHeader createInvoice(InvoiceReq request) throws Exception {
        validator.validateInvoicePayload(request);
        InvoiceHeader header = request.getHeader();
        header = headerRepository.save(header);
        for (Item item: request.getInvoiceItems()) {
            item.setHeaderId(header.getId());
        }
        itemRepository.saveAll(request.getInvoiceItems());
        request.getBillSundry().setHeaderId(header.getId());
        billSundryRepository.save(request.getBillSundry());
        return header;
    }

    public Boolean deleteInvoice(String headerId) {
        Optional<InvoiceHeader> headerOpt = headerRepository.findById(headerId);
        if (headerOpt.isPresent()) {
            InvoiceHeader header = headerOpt.get();
            headerRepository.deleteById(headerId);
            List<InvoiceBillSundry> allBillSundry = billSundryRepository.findAllBillSundryByHeaderId(headerId);
            billSundryRepository.deleteAll(allBillSundry);
            List<Item> allInvoiceItems = itemRepository.getAllItemsByHeaderId(headerId);
            itemRepository.deleteAll(allInvoiceItems);
        }
        return Boolean.TRUE;
    }

    public List<InvoiceResponse> getAllInvoiceResponses() {
        List<InvoiceHeader> allInvoices = headerRepository.findAll();
        List<InvoiceResponse> response = new ArrayList<>();
        for (InvoiceHeader header: allInvoices) {
            InvoiceResponse resp = new InvoiceResponse();
            resp.setId(header.getId());
            resp.setInvoiceHeader(header);
            List<Item> allItems = itemRepository.getAllItemsByHeaderId(header.getId());
            resp.setInvoiceItems(allItems);
            List<InvoiceBillSundry> billSundries = billSundryRepository.findAllBillSundryByHeaderId(header.getId());
            resp.setInvoiceBillSundryList(billSundries);
        }
        return response;
    }

    public InvoiceResponse getInvoiceById(String headerId) {
        Optional<InvoiceHeader> invoice = headerRepository.findById(headerId);
        InvoiceResponse resp = new InvoiceResponse();
        if (invoice.isPresent()) {
            InvoiceHeader header = invoice.get();
            resp.setId(header.getId());
            resp.setInvoiceHeader(header);
            List<Item> allItems = itemRepository.getAllItemsByHeaderId(header.getId());
            resp.setInvoiceItems(allItems);
            List<InvoiceBillSundry> billSundries = billSundryRepository.findAllBillSundryByHeaderId(header.getId());
            resp.setInvoiceBillSundryList(billSundries);
        }
        return resp;
    }

    public InvoiceHeader updateInvoice(String headerId, InvoiceReq request) throws Exception {
        validator.validateInvoicePayload(request);
        Optional<InvoiceHeader> headerOpt = headerRepository.findById(headerId);
        if (headerOpt.isPresent()) {
            InvoiceHeader header = headerOpt.get();
            List<Item> items = itemRepository.getAllItemsByHeaderId(headerId);
            for (Item newItem: request.getInvoiceItems()) {
                Boolean isNewItem = Boolean.TRUE;
                for (Item item: items) {
                    if (newItem.getId().equals(item.getId())) {
                        isNewItem = Boolean.FALSE;
                        item.setName(newItem.getName());
                        item.setPrice(newItem.getPrice());
                        item.setQuantity(newItem.getQuantity());
                        item.setAmount(newItem.getAmount());
                        itemRepository.save(item);
                    }
                }
                if (isNewItem.equals(Boolean.TRUE)) {
                    itemRepository.save(newItem);
                }
            }
            List<InvoiceBillSundry> sundries = billSundryRepository.findAllBillSundryByHeaderId(headerId);
            if(sundries != null && !sundries.isEmpty()) {
                InvoiceBillSundry sundry = sundries.get(0);
                sundry.setAmount(request.getBillSundry().getAmount());
                sundry.setBillSundryName(request.getBillSundry().getBillSundryName());
                billSundryRepository.save(sundry);
            } else {
                request.getBillSundry().setHeaderId(headerId);
                billSundryRepository.save(request.getBillSundry());
            }
            header.setAmount(request.getHeader().getAmount());
            header.setBillingAddress(request.getHeader().getBillingAddress());
            header.setCustomerName(request.getHeader().getCustomerName());
            header.setGstIN(request.getHeader().getGstIN());
            header.setInvoiceNumber(request.getHeader().getInvoiceNumber());
            header.setShippingAddress(request.getHeader().getShippingAddress());
            return headerRepository.save(header);
        }
        return null;
    }
}
