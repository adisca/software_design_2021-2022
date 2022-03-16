package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name = "vacation_packages", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "id_destination"})})
public class VacationPackage {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private Date periodStart;

    @Column
    private Date periodEnd;

    @Column
    private String extraDetails;

    @Column
    private Integer maxPeople;

    @ManyToOne
    @JoinColumn(name = "id_destination")
    private VacationDestination destination;

    @ManyToMany(mappedBy = "packages")
    private List<User> users;

    public VacationPackage() {}

    public VacationPackage(String name, Integer price, Date periodStart, Date periodEnd, String extraDetails,
                           Integer maxPeople, VacationDestination destination) {
        this.name = name;
        this.price = price;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.extraDetails = extraDetails;
        this.maxPeople = maxPeople;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public VacationDestination getDestination() {
        return destination;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setDestination(VacationDestination destination) {
        this.destination = destination;
    }

    public Vector<String> toVector() {
        Vector<String> vec = new Vector<>();
        vec.add(id.toString());
        vec.add(name);
        vec.add(price.toString());
        vec.add(periodStart.toString());
        vec.add(periodEnd.toString());
        vec.add(extraDetails);
        vec.add(maxPeople.toString());
        vec.add(destination.getName());
        return vec;
    }
}
