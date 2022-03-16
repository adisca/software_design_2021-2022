package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_package",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id"))
    private List<VacationPackage> packages;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
