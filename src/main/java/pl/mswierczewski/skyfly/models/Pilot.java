package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.models.enums.AirplaneType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "pilots")
@DiscriminatorValue("Pilot")
public class Pilot extends Employee {

    @Column(nullable = false, unique = true)
    private String pilotLicenseNumber;

    @ElementCollection(targetClass = AirplaneType.class)
    @JoinTable(name = "pilot_permissions", joinColumns = @JoinColumn(name = "pilot_id"))
    @Column(name = "permission", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<AirplaneType> permissions = new HashSet<>();

    public Pilot(){ }

    public Pilot(LocalDate dateOfEmployment, Double basicSalary, String pilotLicenseNumber, Set<AirplaneType> permissions){
        super(dateOfEmployment, basicSalary);
        this.pilotLicenseNumber = pilotLicenseNumber;
        this.permissions = permissions;
    }

    public Pilot(LocalDate dateOfEmployment, Double basicSalary, String pilotLicenseNumber, AirplaneType permission){
        super(dateOfEmployment, basicSalary);
        this.pilotLicenseNumber = pilotLicenseNumber;
        this.permissions.add(permission);
    }

    public String getPilotLicenseNumber() {
        return pilotLicenseNumber;
    }

    public void setPilotLicenseNumber(String pilotLicenseNumber) {
        this.pilotLicenseNumber = pilotLicenseNumber;
    }

    public Set<AirplaneType> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<AirplaneType> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "pilotLicenseNumber='" + pilotLicenseNumber + '\'' +
                ", permissions=" + permissions +
                ", dateOfEmployment=" + dateOfEmployment +
                ", basicSalary=" + basicSalary +
                '}';
    }
}
