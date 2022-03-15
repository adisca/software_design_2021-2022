package repository;

import model.VacationDestination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VacationDestinationRepo {
    private final EntityManagerFactory entityManagerFactory;

    public VacationDestinationRepo(String persistenceUnitName) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public void addDestination(VacationDestination vacationDestination) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationDestination);
        em.getTransaction().commit();
        em.close();
    }

    public void removeDestination(Long id) {

    }
}
