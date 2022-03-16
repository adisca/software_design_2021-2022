package model;

import javax.persistence.*;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name = "vacation_destinations")
public class VacationDestination {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")
    private List<VacationPackage> packages;

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

    public Vector<String> toVector() {
        Vector<String> vec = new Vector<>();
        vec.add(id.toString());
        vec.add(name);
        return vec;
    }
}
