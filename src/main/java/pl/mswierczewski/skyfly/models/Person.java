package pl.mswierczewski.skyfly.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "person_type")
public abstract class Person implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String secondName; //optional

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String identityCardNumber;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "contact_details", nullable = false)
    private ContactDetails contactDetails;

    public Person(){ }

    public Person(String firstName, String secondName, String lastName, String identityCardNumber, LocalDate birthDate){
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.identityCardNumber = identityCardNumber;
        this.birthDate = birthDate;
    }

    public Person(String firstName, String lastName, String identityCardNumber, LocalDate birthDate){
        this(firstName, null, lastName, identityCardNumber, birthDate);
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

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
