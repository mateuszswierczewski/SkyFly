package pl.mswierczewski.skyfly.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity(name = "contacts_details")
public class ContactDetails implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Integer id;

    @Column(nullable = false)
    private String adress;

    @Column(nullable = false)
    private Integer phoneNumber;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @OneToOne(mappedBy = "contactDetails")
    @Fetch(FetchMode.SELECT)
    private Person person;

    public ContactDetails(){ }

    public ContactDetails(String adress, Integer phoneNumber, String email){
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }
}
