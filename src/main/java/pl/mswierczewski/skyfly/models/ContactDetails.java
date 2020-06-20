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

    @NotNull(message = "The address cannot be empty")
    private String address;

    @NotNull(message = "Please give your phone number")
    private Integer phoneNumber;

    @NotNull(message = "Please give your email")
    @Email(message = "This email is not valid")
    @Unique(serviceClass = ContactDetailsService.class, columnName = "email",
            message = "This email is already taken")
    private String email;

    @OneToOne(mappedBy = "contactDetails")
    @Fetch(FetchMode.SELECT)
    private SkyFlyUser user;

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

    public SkyFlyUser getUser() {
        return user;
    }

    public void setUser(SkyFlyUser user) {
        this.user = user;
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
