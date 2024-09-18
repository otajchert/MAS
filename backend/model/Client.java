package com.zbyszkobud.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client extends Person {
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @OneToMany(mappedBy = "client")
    private List<Invoice> invoices;

    public Client() {}

    public Client(String firstName, String lastName, ContactData contactData) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setContactData(contactData);
    }

    // Gettery i settery
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}