package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.models.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class UserRole implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType role;

    // basic getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
