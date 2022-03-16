package repository;

import model.User;
import model.VacationDestination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

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
        em.getTransaction().begin();
        em.remove(em.find(VacationDestination.class, id));
        em.getTransaction().commit();
        em.close();
    }

    public List<VacationDestination> viewAllDestinations() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT d FROM VacationDestination d", VacationDestination.class).getResultList();
    }

    public VacationDestination getDestinationByID(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT d FROM VacationDestination d WHERE d.id = :id", VacationDestination.class).
                setParameter("id", id).getSingleResult();
    }
}
