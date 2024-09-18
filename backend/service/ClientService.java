package com.zbyszkobud.service;

import com.zbyszkobud.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ApplicationFacade applicationFacade;

    public List<Client> getAllClients() {
        return applicationFacade.getClients();
    }

    public Client getClientById(Long id) {
        return applicationFacade.getClients().stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Client createClient(Client client) {
        applicationFacade.addClient(client);
        return client;
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client client = getClientById(id);
        if (client != null) {
            client.setFirstName(clientDetails.getFirstName());
            client.setLastName(clientDetails.getLastName());
            client.setContactData(clientDetails.getContactData());
            applicationFacade.updateClient(client);
        }
        return client;
    }

    public boolean deleteClient(Long id) {
        return applicationFacade.deleteClient(id);
    }
}
