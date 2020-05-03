package pl.mswierczewski.skyfly.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "employees")
@DiscriminatorColumn(name = "profession")
@DiscriminatorValue("Employee")
public abstract class Employee extends Person implements Serializable {

    @Column(nullable = false)
    private LocalDate dateOfEmployment;

    @Column(nullable = false)
    private Double basicSalary;

    @Column(nullable = false, unique = true)
    private String pilotLicenseNumber;

}
