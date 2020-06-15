package pl.mswierczewski.skyfly.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.mswierczewski.skyfly.services.ContactDetailsService;
import pl.mswierczewski.skyfly.validation.constraints.Unique;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "contacts_details")
public class ContactDetails implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Integer id;

    @NotNull(message = "{pl.mswierczewski.skyfly.models.ContactDetails.address.NotNull.message}")
    private String address;

    @NotNull(message = "{pl.mswierczewski.skyfly.models.ContactDetails.phoneNumber.NotNull.message}")
    private Integer phoneNumber;

    @NotNull(message = "{pl.mswierczewski.skyfly.models.ContactDetails.email.NotNull.message}")
    @Email(message = "{pl.mswierczewski.skyfly.models.ContactDetails.email.Email.message}")
    @Unique(serviceClass = ContactDetailsService.class, columnName = "email",
            message = "{pl.mswierczewski.skyfly.models.ContactDetails.email.Unique.message}")
    private String email;

    @OneToOne(mappedBy = "contactDetails")
    @Fetch(FetchMode.SELECT)
    private Person person;

    public ContactDetails(){ }

    public ContactDetails(String address, Integer phoneNumber, String email){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "ContactDetails{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
