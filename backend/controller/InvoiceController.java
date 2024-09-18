package com.zbyszkobud.controller;

import com.zbyszkobud.model.Invoice;
import com.zbyszkobud.service.ApplicationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private ApplicationFacade applicationFacade;

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return applicationFacade.getInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = applicationFacade.getInvoices().stream()
                                           .filter(i -> i.getId().equals(id))
                                           .findFirst()
                                           .orElse(null);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        applicationFacade.addInvoice(invoice);
        return invoice;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoiceDetails) {
        Invoice invoice = applicationFacade.getInvoices().stream()
                                           .filter(i -> i.getId().equals(id))
                                           .findFirst()
                                           .orElse(null);
        if (invoice != null) {
            invoice.setDescription(invoiceDetails.getDescription());
            invoice.setIssueDate(invoiceDetails.getIssueDate());
            invoice.setClient(invoiceDetails.getClient());
            invoice.setProject(invoiceDetails.getProject());
            applicationFacade.updateInvoice(invoice);
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        boolean deleted = applicationFacade.deleteInvoice(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
