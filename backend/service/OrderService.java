package com.zbyszkobud.service;

import com.zbyszkobud.model.Invoice;
import com.zbyszkobud.model.Order;
import com.zbyszkobud.model.Project;
import com.zbyszkobud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ApplicationFacade applicationFacade;

    public List<Order> getAllOrders() {
        return applicationFacade.getOrders();
    }

    public Order getOrderById(Long id) {
        return applicationFacade.getOrders().stream()
        .filter(i -> i.getId().equals(id))
        .findFirst()
        .orElse(null);
}
    

    public Order createOrder(Order order) {
        applicationFacade.addOrder(order);
        return order;
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        if (order != null) {
            order.setAvailabilityFrom(orderDetails.getAvailabilityFrom());
            order.setAvailabilityTo(orderDetails.getAvailabilityTo());
            order.setWorkScopeDescription(orderDetails.getWorkScopeDescription());
            order.setClient(orderDetails.getClient());
            order.setProject(orderDetails.getProject());
            applicationFacade.updateOrder(order);
        }
        return order;
    }

    public boolean deleteOrder(Long id) {
       return applicationFacade.deleteOrder(id);
    }

    public Project getOrderProject(Long orderId) {
        Order order = getOrderById(orderId);
        return order != null ? order.getProject() : null;
    }
}
