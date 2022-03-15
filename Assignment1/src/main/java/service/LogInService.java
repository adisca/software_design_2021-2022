package service;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import repository.UserRepo;

import javax.persistence.NoResultException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

public class LogInService {
    private final UserRepo repo;

    public LogInService() {
        repo = new UserRepo();
    }

    public void logIn(String username, String password) throws Exception {
        try {
            User forChecking = repo.getUserByUsername(username);
            if (!Objects.equals(forChecking.getPassword(), password))
                throw (new Exception("Wrong password"));
        } catch (NoResultException e) {
            throw (new Exception("Nonexistent username"));
        }
    }

    public void register(String username, String password) throws Exception {
        try {
            repo.addUser(new User(username, password));
        } catch (Exception e) {
            throw (new Exception("The username is taken"));
        }
    }

}
