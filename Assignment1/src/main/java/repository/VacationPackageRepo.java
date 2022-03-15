package repository;

import model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

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
        try {
            em.createQuery("DELETE FROM vacation_packages WHERE id = " + id.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.getTransaction().commit();
        em.close();
    }

    public void editPackage() {

    }

    public ArrayList<VacationPackage> getPackages() {

        return null;
    }
}
