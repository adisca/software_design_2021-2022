package repository;

import model.VacationDestination;
import model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class VacationPackageRepo {
    private static final String PERSISTENCE_UNIT_NAME = "SD.Assignment1";

    private final EntityManagerFactory entityManagerFactory;

    public VacationPackageRepo() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public void addPackage(VacationPackage vacationPackage) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationPackage);
        em.getTransaction().commit();
        em.close();
    }

    public void removePackage(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM VacationPackage WHERE id = :id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void editPackage(VacationPackage vacationPackage) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(vacationPackage);
        em.getTransaction().commit();
        em.close();
    }

    public List<VacationPackage> getAllPackages() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT p FROM VacationPackage p", VacationPackage.class).getResultList();
    }

    public List<VacationPackage> getPackagesOfDestination(VacationDestination dst) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT p FROM VacationPackage p WHERE p.destination = :dst", VacationPackage.class).
            setParameter("dst", dst).getResultList();
    }

    public VacationPackage getPackageByID(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT p FROM VacationPackage p WHERE p.id = :id", VacationPackage.class).
                setParameter("id", id).getSingleResult();
    }
}
