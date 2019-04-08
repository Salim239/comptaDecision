package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A QuittanceMensuelleImpot.
 */
@Entity
@Table(name = "quittance_mensuelle_impot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuittanceMensuelleImpot implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @NotNull
    @Column(name = "mois", nullable = false)
    private Integer mois;

    @Column(name = "numero_quittance")
    private String numeroQuittance;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(name = "montant_paye", precision = 10, scale = 2)
    private BigDecimal montantPaye;

    @ManyToOne
    @JsonIgnoreProperties("quittanceMensuelleImpots")
    private FicheClient ficheClient;

    @OneToMany(mappedBy = "quittanceMensuelleImpot", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<QuittanceMensuelleImpotLine> quittanceMensuelleImpotLines = new ArrayList<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public QuittanceMensuelleImpot annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getMois() {
        return mois;
    }

    public QuittanceMensuelleImpot mois(Integer mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public QuittanceMensuelleImpot numeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
        return this;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public QuittanceMensuelleImpot datePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
        return this;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public QuittanceMensuelleImpot montantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
        return this;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public QuittanceMensuelleImpot ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }

    @Override
    public String toString() {
        return "QuittanceMensuelleImpot{" +
                "id=" + id +
                ", annee=" + annee +
                ", mois=" + mois +
                ", numeroQuittance='" + numeroQuittance + '\'' +
                ", datePaiement=" + datePaiement +
                ", montantPaye=" + montantPaye +
                ", ficheClient=" + ficheClient +
                ", quittanceMensuelleImpotLines=" + quittanceMensuelleImpotLines +
                '}';
    }

    public List<QuittanceMensuelleImpotLine> getQuittanceMensuelleImpotLines() {
        return quittanceMensuelleImpotLines;
    }

    public void setQuittanceMensuelleImpotLines(List<QuittanceMensuelleImpotLine> quittanceMensuelleImpotLines) {
        this.quittanceMensuelleImpotLines = quittanceMensuelleImpotLines;
    }

    public void addQuittanceMensuelleImpotLine(QuittanceMensuelleImpotLine quittanceMensuelleImpotLine) {
        quittanceMensuelleImpotLines.add(quittanceMensuelleImpotLine);
        quittanceMensuelleImpotLine.setQuittanceMensuelleImpot(this);
    }

    public void removeQuittanceMensuelleImpotLine(QuittanceMensuelleImpotLine quittanceMensuelleImpotLine) {
        quittanceMensuelleImpotLines.remove(quittanceMensuelleImpotLine);
        quittanceMensuelleImpotLine.setQuittanceMensuelleImpot(null);
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuittanceMensuelleImpot quittanceMensuelleImpot = (QuittanceMensuelleImpot) o;
        if (quittanceMensuelleImpot.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quittanceMensuelleImpot.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
