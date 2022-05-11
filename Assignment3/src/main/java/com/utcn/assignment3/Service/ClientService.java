package com.utcn.assignment3.Service;

import com.utcn.assignment3.Model.Client;
import com.utcn.assignment3.Model.Order;
import com.utcn.assignment3.Repo.ClientRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for client
 */
@Service
public class ClientService {

    private final ClientRepo repo;

    public ClientService(ClientRepo repo) {
        this.repo = repo;
    }

    /**
     * Finds a client by id
     *
     * @param id    the id of the client
     * @return      the client if found, null otherwise
     */
    public Client find(Long id) {
        return repo.getById(id);
    }

    /**
     * Gets the orders of a client
     *
     * @param id    the id of the client
     * @return      a list of orders belonging to the client
     */
    public List<Order> getOrders(Long id) {
        return repo.getById(id).getOrders();
    }

}
