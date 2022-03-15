package repository;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserRepo {
    private static final String PERSISTENCE_UNIT_NAME = "SD.Assignment1";

    private final EntityManagerFactory entityManagerFactory;

    public UserRepo() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public void addUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public User getUserByUsername(String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        User user = em.createQuery("SELECT u from User u where u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
        return user;
    }


}
