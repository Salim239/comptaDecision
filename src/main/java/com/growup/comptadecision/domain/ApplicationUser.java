package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Application User
 */
@Entity
@Table(name = "application_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "matricule")
    private String matricule;

    @NotNull
    @Column(name = "poste", nullable = false)
    private String poste;

    @Column(name = "date_embauche")
    private String dateEmbauche;

    @ManyToOne
    @JsonIgnoreProperties("applicationUsers")
    private CabinetComptable cabinetComptable;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public ApplicationUser matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPoste() {
        return poste;
    }

    public ApplicationUser poste(String poste) {
        this.poste = poste;
        return this;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public ApplicationUser dateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        return this;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public CabinetComptable getCabinetComptable() {
        return cabinetComptable;
    }

    public ApplicationUser cabinetComptable(CabinetComptable cabinetComptable) {
        this.cabinetComptable = cabinetComptable;
        return this;
    }

    public void setCabinetComptable(CabinetComptable cabinetComptable) {
        this.cabinetComptable = cabinetComptable;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUser)) {
            return false;
        }
        return id != null && id.equals(((ApplicationUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", poste='" + getPoste() + "'" +
            ", dateEmbauche='" + getDateEmbauche() + "'" +
            "}";
    }
}
