package pl.mswierczewski.skyfly.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Stewards")
public class Steward extends Employee implements Serializable {

    @OneToMany(mappedBy = "steward")
    private final List<LanguageProficiency> languages = new ArrayList<>();

}
