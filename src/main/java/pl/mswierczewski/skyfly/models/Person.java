package pl.mswierczewski.skyfly.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "person_type")
public abstract class Person implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Long id;

    @NotNull
    @Size(min = 2, message = "{pl.mswierczewski.skyfly.models.Person.firstName.Size.message}")
    private String firstName;

    private String secondName; //optional

    @NotNull
    @Size(min = 2, message = "{pl.mswierczewski.skyfly.models.Person.lastName.Size.message}")
    private String lastName;

//    @Column(nullable = false, unique = true)
//    private String identityCardNumber;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "{pl.mswierczewski.skyfly.models.Person.birthDate.Past.message}")
    private LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_details")
    private ContactDetails contactDetails;

    public Person(){ }

    public Person(String firstName, String secondName, String lastName, /*String identityCardNumber,*/ LocalDate birthDate){
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
       // this.identityCardNumber = identityCardNumber;
        this.birthDate = birthDate;
    }

    public Person(String firstName, String lastName, /*String identityCardNumber,*/ LocalDate birthDate){
        this(firstName, null, lastName, /*identityCardNumber,*/ birthDate);
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

//    public String getIdentityCardNumber() {
//        return identityCardNumber;
//    }
//
//    public void setIdentityCardNumber(String identityCardNumber) {
//        this.identityCardNumber = identityCardNumber;
//    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        contactDetails.setPerson(this);
        this.contactDetails = contactDetails;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                //", identityCardNumber='" + identityCardNumber + '\'' +
                ", birthDate=" + birthDate +
                ", contactDetails=" + contactDetails +
                '}';
    }
}
