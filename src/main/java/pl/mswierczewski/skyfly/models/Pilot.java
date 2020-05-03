package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.models.enums.AirplaneType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Pilots")
@DiscriminatorValue("Pilot")
public class Pilot extends Employee {

    @Column(nullable = false, unique = true)
    private String pilotLicenseNumber;

    @ElementCollection(targetClass = AirplaneType.class)
    @JoinTable(name = "pilot_permissions", joinColumns = @JoinColumn(name = "pilot_id"))
    @Column(name = "permission", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<AirplaneType> permissions = new ArrayList<>();

    public Pilot(){ }

}
