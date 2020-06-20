package pl.mswierczewski.skyfly.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "employees")
@DiscriminatorColumn(name = "profession")
@DiscriminatorValue("Employee")
public abstract class Employee extends Person implements Serializable {

    @Column(nullable = false)
    protected LocalDate dateOfEmployment;

    @Column(nullable = false)
    protected Double basicSalary;

    public Employee(){}

    public Employee(LocalDate dateOfEmployment, Double basicSalary){
        this.dateOfEmployment = dateOfEmployment;
        this.basicSalary = basicSalary;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public int getSeniorityInYears(){
        return Period.between(dateOfEmployment, LocalDate.now()).getYears();
    }

    public float getSalaryBonus(){
        int salaryBonus = getSeniorityInYears() * 2;

        if (salaryBonus <= 20)
            return (float) salaryBonus/100;
        else
            return 0.2F;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "dateOfEmployment=" + dateOfEmployment +
                ", basicSalary=" + basicSalary +
                '}';
    }
}
