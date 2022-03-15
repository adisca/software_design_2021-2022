package repository;

import model.User;
import model.VacationDestination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VacationDestinationRepo {
    private static final String PERSISTENCE_UNIT_NAME = "SD.Assignment1";

    private final EntityManagerFactory entityManagerFactory;

    public VacationDestinationRepo() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public void addDestination(VacationDestination vacationDestination) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationDestination);
        em.getTransaction().commit();
        em.close();
    }

    public void removeDestination(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.createQuery("DELETE from User where id = :id").setParameter("id", id).executeUpdate();
    }
}
