package pl.mswierczewski.skyfly.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Languages")
public class Language implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String languageName;

    @OneToMany(mappedBy = "language")
    private final List<LanguageProficiency> stewards = new ArrayList<>();

    public Language(){ }

    public Language(String languageName){
        this.languageName = languageName;
    }
}
