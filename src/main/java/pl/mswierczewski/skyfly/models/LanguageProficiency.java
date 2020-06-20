package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.models.enums.LanguageProficiencyLevel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "language_proficiency")
public class LanguageProficiency implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_proficiency_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageProficiencyLevel levelOfLanguageProficiency;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Steward steward;

    public LanguageProficiency(){}

    public LanguageProficiency(Steward steward, Language language, LanguageProficiencyLevel level){
        this.steward = steward;
        this.language = language;
        this.levelOfLanguageProficiency = level;
    }

    public LanguageProficiencyLevel getLevelOfLanguageProficiency() {
        return levelOfLanguageProficiency;
    }

    public void setLevelOfLanguageProficiency(LanguageProficiencyLevel levelOfLanguageProficiency) {
        this.levelOfLanguageProficiency = levelOfLanguageProficiency;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Steward getSteward() {
        return steward;
    }

    public void setSteward(Steward steward) {
        this.steward = steward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LanguageProficiency)) return false;
        LanguageProficiency that = (LanguageProficiency) o;
        return getLevelOfLanguageProficiency() == that.getLevelOfLanguageProficiency() &&
                getLanguage().equals(that.getLanguage()) &&
                getSteward().equals(that.getSteward());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLevelOfLanguageProficiency(), getLanguage(), getSteward());
    }

    @Override
    public String toString() {
        return "LanguageProficiency{" +
                "id=" + id +
                ", levelOfLanguageProficiency=" + levelOfLanguageProficiency +
                ", language=" + language +
                ", steward=" + steward +
                '}';
    }
}
