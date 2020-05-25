package pl.mswierczewski.skyfly.models;

import pl.mswierczewski.skyfly.models.enums.LanguageProficiencyLevel;

import javax.persistence.*;
import java.io.Serializable;

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
}
