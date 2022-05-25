package com.utcn.project.Service;

import com.utcn.project.Model.Admin;
import com.utcn.project.Model.User;
import com.utcn.project.Repo.AdminRepo;
import com.utcn.project.Repo.UserRepo;
import com.utcn.project.Security.Encryption.Encryptor;
import com.utcn.project.Security.Encryption.EncryptorProxy;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LogInService {
    private final UserRepo userRepo;
    private final AdminRepo adminRepo;

    private final Encryptor encryptor;

    public LogInService(UserRepo userRepo, AdminRepo adminRepo) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;

        this.encryptor = new EncryptorProxy();
    }

    /**
     * Checks if the user exists in the database and if the password is correct.
     *
     * @param user    The user, with his credentials, that wants to log in
     * @return          The id of the user, or -1 if the log in failed
     */
    public User logInUser(User user) {
        User user2 = userRepo.findByUsername(user.getUsername());
        if (user2 != null && Objects.equals(encryptor.encrypt(user.getPassword()), user2.getPassword())) {
            return user2;
        }
        return null;
    }

    /**
     * Creates a new user in the database, with the provided credentials
     *
     * @param user The user to be added, credentials are required
     * @return      True if success, false otherwise
     */
    public Boolean signUpUser(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null || user.getFirstName() == null ||
                user.getLastName() == null || user.getUsername().equals("") || user.getPassword().equals("") ||
                user.getFirstName().equals("") || user.getLastName().equals(""))
            return Boolean.FALSE;
        user.setPassword(encryptor.encrypt(user.getPassword()));
        if (userRepo.findByUsername(user.getUsername()) == null &&
                adminRepo.findByUsername(user.getUsername()) == null) {
            userRepo.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks if the admin exists in the database and if the password is correct.
     *
     * @param admin     The admin, with his credentials, that wants to log in
     * @return          The id of the admin, or -1 if the log in failed
     */
    public Long logInAdmin(Admin admin) {
        Admin admin2 = adminRepo.findByUsername(admin.getUsername());
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
        if (admin == null || admin.getUsername() == null || admin.getPassword() == null ||
                admin.getUsername().equals("") || admin.getPassword().equals(""))
            return Boolean.FALSE;

        admin.setPassword(encryptor.encrypt(admin.getPassword()));
        if (userRepo.findByUsername(admin.getUsername()) == null &&
                adminRepo.findByUsername(admin.getUsername()) == null) {
            adminRepo.save(admin);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
