package pl.mswierczewski.skyfly.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;
        Language language = (Language) o;
        return getLanguageName().equals(language.getLanguageName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLanguageName());
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageName='" + languageName + '\'' +
                ", stewards=" + stewards +
                '}';
    }
}
