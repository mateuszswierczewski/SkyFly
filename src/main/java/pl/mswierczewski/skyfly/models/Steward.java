package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.models.enums.LanguageProficiencyLevel;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stewards")
public class Steward extends Employee implements Serializable {

    @OneToMany(mappedBy = "steward")
    private final List<LanguageProficiency> languages = new ArrayList<>();

    public Steward(){
    }

    public Steward(LocalDate dateOfEmployment, Double basicSalary, Language language, LanguageProficiencyLevel level){
        super(dateOfEmployment, basicSalary);

        LanguageProficiency languageProficiency = new LanguageProficiency(this, language, level);

        if (!languages.contains(languageProficiency))
            languages.add(languageProficiency);
    }

    public List<LanguageProficiency> getLanguages() {
        return languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Steward)) return false;
        Steward steward = (Steward) o;
        return getLanguages().equals(steward.getLanguages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLanguages());
    }

    @Override
    public String toString() {
        return "Steward{" +
                "languages=" + languages +
                ", dateOfEmployment=" + dateOfEmployment +
                ", basicSalary=" + basicSalary +
                '}';
    }
}
