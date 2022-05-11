package com.utcn.assignment3.Service;

import com.utcn.assignment3.Model.Admin;
import com.utcn.assignment3.Model.Client;
import com.utcn.assignment3.Repo.AdminRepo;
import com.utcn.assignment3.Repo.ClientRepo;
import com.utcn.assignment3.Security.Encryption.Encryptor;
import com.utcn.assignment3.Security.Encryption.EncryptorProxy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Service class for log in/sign up operations
 */
@Service
public class LogInService implements UserDetailsService {
    private final AdminRepo adminRepo;
    private final ClientRepo clientRepo;

    private final Encryptor encryptor;

    public LogInService(AdminRepo adminRepo, ClientRepo clientRepo) {
        this.adminRepo = adminRepo;
        this.clientRepo = clientRepo;

        this.encryptor = new EncryptorProxy();
    }

    /**
     * Checks if the client exists in the database and the password is correct.
     *
     * @param client    The client, with his credentials, that wants to log in
     * @return          The id of the client, or -1 if the log in failed
     */
    public Long logInClient(Client client) {
        Client client2 = clientRepo.findByUsername(client.getUsername());
        if (client2 != null && Objects.equals(encryptor.encrypt(client.getPassword()), client2.getPassword())) {
            return client2.getId();
        }
        return -1L;
    }

    /**
     * Creates a new client in the database, with the provided credentials
     *
     * @param client The client to be added, credentials are required
     * @return      True if success, false otherwise
     */
    public Boolean signUpClient(Client client) {
        client.setPassword(encryptor.encrypt(client.getPassword()));
        if (clientRepo.findByUsername(client.getUsername()) == null &&
                adminRepo.findByName(client.getUsername()) == null) {
            clientRepo.save(client);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks if the admin exists in the database and the password is correct.
     *
     * @param admin     The admin, with his credentials, that wants to log in
     * @return          The id of the admin, or -1 if the log in failed
     */
    public Long logInAdmin(Admin admin) {
        Admin admin2 = adminRepo.findByName(admin.getName());
        if (admin2 != null && Objects.equals(encryptor.encrypt(admin.getPassword()), admin2.getPassword())) {
            return admin2.getId();
        }
        return -1L;
    }

    /**
     * Creates a new admin in the database, with the provided credentials
     *
     * @param admin The admin to be added, credentials are required
     * @return      True if success, false otherwise
     */
    public Boolean signUpAdmin(Admin admin) {
        admin.setPassword(encryptor.encrypt(admin.getPassword()));
        if (clientRepo.findByUsername(admin.getName()) == null &&
                adminRepo.findByName(admin.getName()) == null) {
            adminRepo.save(admin);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Searches for a username in the database, both in the admin and client repositories,
     * returning their details if found.
     *
     * @param username  The username to be searched by
     * @return          The details of the found user, exception thrown otherwise
     * @throws UsernameNotFoundException    The username doesn't exist in the database, neither client nor admin
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByName(username);
        Client client = clientRepo.findByUsername(username);
        if (admin != null) {
            return new User(admin.getName(), admin.getPassword(), new ArrayList<>());
        }
        else if (client != null) {
            return new User(client.getUsername(), client.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("No such user found");
    }

}
