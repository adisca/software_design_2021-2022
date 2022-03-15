package model;

import javax.persistence.*;

@Entity
@Table(name = "vacation_destinations")
public class VacationDestination {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    public VacationDestination() {}

    public VacationDestination(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
