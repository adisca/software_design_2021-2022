package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Client;
import com.utcn.assignment2.Model.Order;
import com.utcn.assignment2.Repo.ClientRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepo repo;

    public ClientService(ClientRepo repo) {
        this.repo = repo;
    }

    public Client find(Long id) {
        return repo.getById(id);
    }

    public List<Order> getOrders(Long id) {
        return repo.getById(id).getOrders();
    }

}
