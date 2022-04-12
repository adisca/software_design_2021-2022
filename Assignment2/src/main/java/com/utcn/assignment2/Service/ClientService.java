package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Client;
import com.utcn.assignment2.Repo.ClientRepo;
import com.utcn.assignment2.Security.Encryptor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientService {

    private final ClientRepo repo;

    public ClientService(ClientRepo repo) {
        this.repo = repo;
    }

}
