package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Admin;
import com.utcn.assignment2.Model.Client;
import com.utcn.assignment2.Repo.AdminRepo;
import com.utcn.assignment2.Repo.ClientRepo;
import com.utcn.assignment2.Security.Encryption.Encryptor;
import com.utcn.assignment2.Security.Encryption.EncryptorProxy;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LogInService {
    private final AdminRepo adminRepo;
    private final ClientRepo clientRepo;

    private final Encryptor encryptor;

    public LogInService(AdminRepo adminRepo, ClientRepo clientRepo) {
        this.adminRepo = adminRepo;
        this.clientRepo = clientRepo;

        this.encryptor = new EncryptorProxy();
    }

    public Long logInClient(Client client) {
        Client client2 = clientRepo.findByUsername(client.getUsername());
        if (client2 != null && Objects.equals(encryptor.encrypt(client.getPassword()), client2.getPassword())) {
            return client2.getId();
        }
        return -1L;
    }

    public Boolean signUpClient(Client client) {
        client.setPassword(encryptor.encrypt(client.getPassword()));
        if (clientRepo.findByUsername(client.getUsername()) == null &&
                adminRepo.findByName(client.getUsername()) == null) {
            clientRepo.save(client);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Long logInAdmin(Admin admin) {
        Admin admin2 = adminRepo.findByName(admin.getName());
        if (admin2 != null && Objects.equals(encryptor.encrypt(admin.getPassword()), admin2.getPassword())) {
            return admin2.getId();
        }
        return -1L;
    }

    public Boolean signUpAdmin(Admin admin) {
        admin.setPassword(encryptor.encrypt(admin.getPassword()));
        if (clientRepo.findByUsername(admin.getName()) == null &&
                adminRepo.findByName(admin.getName()) == null) {
            adminRepo.save(admin);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
