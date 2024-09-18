package com.zbyszkobud.service;

import com.zbyszkobud.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private ApplicationFacade applicationFacade;

    public List<Invoice> getAllInvoices() {
        return applicationFacade.getInvoices();
    }

    public Invoice getInvoiceById(Long id) {
        return applicationFacade.getInvoices().stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Invoice createInvoice(Invoice invoice) {
        applicationFacade.addInvoice(invoice);
        return invoice;
    }

    public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
        Invoice invoice = getInvoiceById(id);
        if (invoice != null) {
            invoice.setDescription(invoiceDetails.getDescription());
            invoice.setIssueDate(invoiceDetails.getIssueDate());
            invoice.setClient(invoiceDetails.getClient());
            invoice.setProject(invoiceDetails.getProject());
            applicationFacade.updateInvoice(invoice);
        }
        return invoice;
    }

    public boolean deleteInvoice(Long id) {
        return applicationFacade.deleteInvoice(id);
    }
}
