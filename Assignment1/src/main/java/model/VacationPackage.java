package model;

import javax.persistence.*;

@Entity
@Table(name = "vacation_packages")
public class VacationPackage {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private Integer price;

    @Column
    private String extraDetails;

    @Column
    private Integer maxPeople;

    @ManyToOne
    @JoinColumn(name = "id_destination")
    private VacationDestination destination;

    public VacationPackage() {}

    public VacationPackage(String name, Integer price, String extraDetails, Integer maxPeople,
                           VacationDestination destination) {
        this.name = name;
        this.price = price;
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

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setDestination(VacationDestination destination) {
        this.destination = destination;
    }
}
