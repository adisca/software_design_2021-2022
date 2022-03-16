package service;

import model.VacationDestination;
import model.VacationPackage;
import repository.VacationDestinationRepo;
import repository.VacationPackageRepo;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

public class AgencyService {
    private final VacationDestinationRepo destinationRepo = new VacationDestinationRepo();
    private final VacationPackageRepo packageRepo = new VacationPackageRepo();

    public void addDestination(String name) {
        destinationRepo.addDestination(new VacationDestination(name));
    }

    public void deleteDestination(String id) {
        destinationRepo.removeDestination(Long.valueOf(id));
    }

    public Vector<Vector<String>> viewAllDestinations() {
        Vector<Vector<String>> vec = new Vector<>();
        for (VacationDestination destination : destinationRepo.viewAllDestinations()) {
            vec.add(destination.toVector());
        }
        return vec;
    }

    public void addPackage(String name, String price, String periodS, String periodE, String details, String maxPeople,
                           String destID) {
        VacationDestination vacationDestination = destinationRepo.getDestinationByID(Long.valueOf(destID));
        VacationPackage vacationPackage = new VacationPackage(name, Integer.valueOf(price), Date.valueOf(periodS),
                Date.valueOf(periodE), details, Integer.valueOf(maxPeople), vacationDestination);
        packageRepo.addPackage(vacationPackage);
    }

    public void updatePackage(String pkgID, String pkgName, String pkgPrice, String pkgPeriodS, String pkgPeriodE,
                              String pkgDetails, String pkgMaxPeople, String dstID) {
        VacationPackage vacationPackage = packageRepo.getPackageByID(Long.valueOf(pkgID));
        vacationPackage.setName(pkgName);
        vacationPackage.setPrice(Integer.valueOf(pkgPrice));
        vacationPackage.setPeriodStart(Date.valueOf(pkgPeriodS));
        vacationPackage.setPeriodEnd(Date.valueOf(pkgPeriodE));
        vacationPackage.setExtraDetails(pkgDetails);
        vacationPackage.setMaxPeople(Integer.valueOf(pkgMaxPeople));
        vacationPackage.setDestination(destinationRepo.getDestinationByID(Long.valueOf(dstID)));
        packageRepo.editPackage(vacationPackage);
    }

    public void deletePackage(String id) {
        packageRepo.removePackage(Long.valueOf(id));
    }

    public Vector<Vector<String>> viewAllPackages() {
        Vector<Vector<String>> vec = new Vector<>();
        for (VacationPackage vacationPackage : packageRepo.getAllPackages()) {
            vec.add(vacationPackage.toVector());
        }
        return vec;
    }

    public Vector<Vector<String>> viewPackageOfDestination(String destID) {
        Vector<Vector<String>> vec = new Vector<>();
        VacationDestination vacationDestination = destinationRepo.getDestinationByID(Long.valueOf(destID));
        for (VacationPackage vacationPackage : packageRepo.getPackagesOfDestination(vacationDestination)) {
            vec.add(vacationPackage.toVector());
        }
        return vec;
    }

}
