package com.utcn.assignment3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.utcn.assignment3.Repo.*;
import com.utcn.assignment3.Service.LogInService;
import com.utcn.assignment3.Util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    AdminRepo adminRepo;
    @Mock
    ClientRepo clientRepo;
    @Mock
    CategoryRepo categoryRepo;
    @Mock
    FoodRepo foodRepo;
    @Mock
    OrderRepo orderRepo;
    @Mock
    RestaurantRepo restaurantRepo;

    @Test
    public void loginAdminTest() {
        assertNotNull(adminRepo);
        when(adminRepo.findByName("user")).thenReturn(TestUtils.setupAdmin(4L, "user", "123", true));

        LogInService service = new LogInService(adminRepo, clientRepo);

        assertEquals(-1L, service.logInAdmin(TestUtils.setupAdmin(null, "user", "abc", false)));}

    @Test
    public void signupAdminTest() {
        assertNotNull(adminRepo);
        when(clientRepo.findByUsername("admin")).thenReturn(null);
        when(adminRepo.findByName("admin")).thenReturn(null);

        LogInService service = new LogInService(adminRepo, clientRepo);

        assertTrue(service.signUpAdmin(TestUtils.setupAdmin(null, "admin", "admin", false)));

    }

    @Test
    public void loginClientTest() {
        assertNotNull(clientRepo);
        when(clientRepo.findByUsername("user")).thenReturn(TestUtils.setupClient(3L, "user", "123", true));

        LogInService service = new LogInService(adminRepo, clientRepo);

        assertEquals(3L, service.logInClient(TestUtils.setupClient(null, "user", "123", false)));
    }

    @Test
    public void signupClientTest() {
        assertNotNull(clientRepo);
        when(clientRepo.findByUsername("user")).thenReturn(TestUtils.setupClient(3L, "user", "123", true));

        LogInService service = new LogInService(adminRepo, clientRepo);

        assertFalse(service.signUpClient(TestUtils.setupClient(null, "user", "abc", false)));
    }

}
