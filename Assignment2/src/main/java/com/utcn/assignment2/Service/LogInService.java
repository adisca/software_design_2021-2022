package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Admin;
import com.utcn.assignment2.Model.Client;
import com.utcn.assignment2.Repo.AdminRepo;
import com.utcn.assignment2.Repo.ClientRepo;
import com.utcn.assignment2.Security.Encryptor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LogInService {
    private final AdminRepo adminRepo;
    private final ClientRepo clientRepo;

    public LogInService(AdminRepo adminRepo, ClientRepo clientRepo) {
        this.adminRepo = adminRepo;
        this.clientRepo = clientRepo;
    }

    public Boolean logInClient(Client client) {
        Client client2 = clientRepo.findByUsername(client.getUsername());
        if (client2 != null && Objects.equals(Encryptor.encrypt(client.getPassword()), client2.getPassword())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean signUpClient(Client client) {
        client.setPassword(Encryptor.encrypt(client.getPassword()));
        if (clientRepo.findByUsername(client.getUsername()) == null &&
                adminRepo.findByName(client.getUsername()) == null) {
            clientRepo.save(client);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean logInAdmin(Admin admin) {
        Admin admin2 = adminRepo.findByName(admin.getName());
        if (admin2 != null && Objects.equals(Encryptor.encrypt(admin.getPassword()), admin2.getPassword())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean signUpAdmin(Admin admin) {
        admin.setPassword(Encryptor.encrypt(admin.getPassword()));
        if (clientRepo.findByUsername(admin.getName()) == null &&
                adminRepo.findByName(admin.getName()) == null) {
            adminRepo.save(admin);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
