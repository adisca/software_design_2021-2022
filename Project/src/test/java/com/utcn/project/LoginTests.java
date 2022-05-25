package com.utcn.project;

import com.utcn.project.Model.Admin;
import com.utcn.project.Model.User;
import com.utcn.project.Repo.AdminRepo;
import com.utcn.project.Repo.UserRepo;
import com.utcn.project.Security.Encryption.EncryptorProxy;
import com.utcn.project.Service.LogInService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginTests {

    @Mock
    AdminRepo adminRepo;
    @Mock
    UserRepo userRepo;

    @Test
    public void loginAdminTest() {
        assertNotNull(adminRepo);

        Admin admin1 = new Admin();
        Admin admin2 = new Admin();

        admin1.setId(3L);
        admin1.setUsername("admin");
        admin2.setUsername("admin");

        EncryptorProxy encryptorProxy = new EncryptorProxy();

        admin1.setPassword(encryptorProxy.encrypt("123"));
        admin2.setPassword("123");

        when(adminRepo.findByUsername("admin")).thenReturn(admin1);

        LogInService service = new LogInService(userRepo, adminRepo);

        assertEquals(3L, service.logInAdmin(admin2));
    }

    @Test
    public void signupAdminTest() {
        assertNotNull(adminRepo);
        when(userRepo.findByUsername("admin")).thenReturn(null);
        when(adminRepo.findByUsername("admin")).thenReturn(null);

        LogInService service = new LogInService(userRepo, adminRepo);

        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123");

        assertTrue(service.signUpAdmin(admin));

    }

    @Test
    public void loginClientTest() {
        assertNotNull(userRepo);

        User user = new User();
        user.setUsername("user");
        user.setPassword("123");

        LogInService service = new LogInService(userRepo, adminRepo);

        assertNotEquals(user, service.logInUser(user));
    }

    @Test
    public void signupClientTest() {
        assertNotNull(userRepo);
        when(userRepo.findByUsername("user")).thenReturn(new User());

        LogInService service = new LogInService(userRepo, adminRepo);

        User user = new User();
        user.setUsername("user");
        user.setPassword("123");

        assertFalse(service.signUpUser(user));
    }

}
