package pl.mswierczewski.skyfly.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "person_type")
public abstract class Person implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    protected Long id;

    @NotNull
    @Size(min = 2, message = "Your first name must contain at least {min} characters")
    protected String firstName;

    protected String secondName; //optional

    @NotNull
    @Size(min = 2, message = "Your last name must contain at least {min} characters")
    protected String lastName;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "The date must be set in the past")
    protected LocalDate birthDate;

    public Person(){ }

    public Person(String firstName, String secondName, String lastName, LocalDate birthDate){
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Person(String firstName, String lastName, LocalDate birthDate){
        this(firstName, null, lastName, birthDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge(){
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
