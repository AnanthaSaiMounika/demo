package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.models.InvoiceBillSundry;
import com.example.demo.data.models.InvoiceHeader;
import com.example.demo.data.models.InvoiceReq;
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

    public List<Invi
}
