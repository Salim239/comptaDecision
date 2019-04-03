package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DeclarationAnnuelle.
 */
@Entity
@Table(name = "declaration_annuelle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeclarationAnnuelle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(name = "numero_quittance")
    private String numeroQuittance;

    @Column(name = "montant_chiffre_affaire_ht", precision = 10, scale = 2)
    private BigDecimal montantChiffreAffaireHT;

    @Column(name = "montant_chiffre_affaire_export", precision = 10, scale = 2)
    private BigDecimal montantChiffreAffaireExport;

    @Column(name = "montant_chiffre_affaire_impot", precision = 10, scale = 2)
    private BigDecimal montantChiffreAffaireImpot;

    @Column(name = "montant_chiffre_affaire_ttc", precision = 10, scale = 2)
    private BigDecimal montantChiffreAffaireTTC;

    @Column(name = "montant_resultat_comptable", precision = 10, scale = 2)
    private BigDecimal montantResultatComptable;

    @Column(name = "montant_deduction_commune", precision = 10, scale = 2)
    private BigDecimal montantDeductionCommune;

    @Column(name = "montant_autre_deduction", precision = 10, scale = 2)
    private BigDecimal montantAutreDeduction;

    @Column(name = "montant_base_imposable", precision = 10, scale = 2)
    private BigDecimal montantBaseImposable;

    @Column(name = "montant_impot_liquide", precision = 10, scale = 2)
    private BigDecimal montantImpotLiquide;

    @Column(name = "montant_acompte_provisionnel", precision = 10, scale = 2)
    private BigDecimal montantAcompteProvisionnel;

    @Column(name = "montant_retenue_source", precision = 10, scale = 2)
    private BigDecimal montantRetenueSource;

    @Column(name = "montant_net_a_paye", precision = 10, scale = 2)
    private BigDecimal montantNetAPaye;

    @ManyToOne
    @JsonIgnoreProperties("declarationAnnuelles")
    private FicheClient ficheClient;

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

    public DeclarationAnnuelle annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public DeclarationAnnuelle datePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
        return this;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public DeclarationAnnuelle numeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
        return this;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimal getMontantChiffreAffaireHT() {
        return montantChiffreAffaireHT;
    }

    public DeclarationAnnuelle montantChiffreAffaireHT(BigDecimal montantChiffreAffaireHT) {
        this.montantChiffreAffaireHT = montantChiffreAffaireHT;
        return this;
    }

    public void setMontantChiffreAffaireHT(BigDecimal montantChiffreAffaireHT) {
        this.montantChiffreAffaireHT = montantChiffreAffaireHT;
    }

    public BigDecimal getMontantChiffreAffaireExport() {
        return montantChiffreAffaireExport;
    }

    public DeclarationAnnuelle montantChiffreAffaireExport(BigDecimal montantChiffreAffaireExport) {
        this.montantChiffreAffaireExport = montantChiffreAffaireExport;
        return this;
    }

    public void setMontantChiffreAffaireExport(BigDecimal montantChiffreAffaireExport) {
        this.montantChiffreAffaireExport = montantChiffreAffaireExport;
    }

    public BigDecimal getMontantChiffreAffaireImpot() {
        return montantChiffreAffaireImpot;
    }

    public DeclarationAnnuelle montantChiffreAffaireImpot(BigDecimal montantChiffreAffaireImpot) {
        this.montantChiffreAffaireImpot = montantChiffreAffaireImpot;
        return this;
    }

    public void setMontantChiffreAffaireImpot(BigDecimal montantChiffreAffaireImpot) {
        this.montantChiffreAffaireImpot = montantChiffreAffaireImpot;
    }

    public BigDecimal getMontantChiffreAffaireTTC() {
        return montantChiffreAffaireTTC;
    }

    public DeclarationAnnuelle montantChiffreAffaireTTC(BigDecimal montantChiffreAffaireTTC) {
        this.montantChiffreAffaireTTC = montantChiffreAffaireTTC;
        return this;
    }

    public void setMontantChiffreAffaireTTC(BigDecimal montantChiffreAffaireTTC) {
        this.montantChiffreAffaireTTC = montantChiffreAffaireTTC;
    }

    public BigDecimal getMontantResultatComptable() {
        return montantResultatComptable;
    }

    public DeclarationAnnuelle montantResultatComptable(BigDecimal montantResultatComptable) {
        this.montantResultatComptable = montantResultatComptable;
        return this;
    }

    public void setMontantResultatComptable(BigDecimal montantResultatComptable) {
        this.montantResultatComptable = montantResultatComptable;
    }

    public BigDecimal getMontantDeductionCommune() {
        return montantDeductionCommune;
    }

    public DeclarationAnnuelle montantDeductionCommune(BigDecimal montantDeductionCommune) {
        this.montantDeductionCommune = montantDeductionCommune;
        return this;
    }

    public void setMontantDeductionCommune(BigDecimal montantDeductionCommune) {
        this.montantDeductionCommune = montantDeductionCommune;
    }

    public BigDecimal getMontantAutreDeduction() {
        return montantAutreDeduction;
    }

    public DeclarationAnnuelle montantAutreDeduction(BigDecimal montantAutreDeduction) {
        this.montantAutreDeduction = montantAutreDeduction;
        return this;
    }

    public void setMontantAutreDeduction(BigDecimal montantAutreDeduction) {
        this.montantAutreDeduction = montantAutreDeduction;
    }

    public BigDecimal getMontantBaseImposable() {
        return montantBaseImposable;
    }

    public DeclarationAnnuelle montantBaseImposable(BigDecimal montantBaseImposable) {
        this.montantBaseImposable = montantBaseImposable;
        return this;
    }

    public void setMontantBaseImposable(BigDecimal montantBaseImposable) {
        this.montantBaseImposable = montantBaseImposable;
    }

    public BigDecimal getMontantImpotLiquide() {
        return montantImpotLiquide;
    }

    public DeclarationAnnuelle montantImpotLiquide(BigDecimal montantImpotLiquide) {
        this.montantImpotLiquide = montantImpotLiquide;
        return this;
    }

    public void setMontantImpotLiquide(BigDecimal montantImpotLiquide) {
        this.montantImpotLiquide = montantImpotLiquide;
    }

    public BigDecimal getMontantAcompteProvisionnel() {
        return montantAcompteProvisionnel;
    }

    public DeclarationAnnuelle montantAcompteProvisionnel(BigDecimal montantAcompteProvisionnel) {
        this.montantAcompteProvisionnel = montantAcompteProvisionnel;
        return this;
    }

    public void setMontantAcompteProvisionnel(BigDecimal montantAcompteProvisionnel) {
        this.montantAcompteProvisionnel = montantAcompteProvisionnel;
    }

    public BigDecimal getMontantRetenueSource() {
        return montantRetenueSource;
    }

    public DeclarationAnnuelle montantRetenueSource(BigDecimal montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
        return this;
    }

    public void setMontantRetenueSource(BigDecimal montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
    }

    public BigDecimal getMontantNetAPaye() {
        return montantNetAPaye;
    }

    public DeclarationAnnuelle montantNetAPaye(BigDecimal montantNetAPaye) {
        this.montantNetAPaye = montantNetAPaye;
        return this;
    }

    public void setMontantNetAPaye(BigDecimal montantNetAPaye) {
        this.montantNetAPaye = montantNetAPaye;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public DeclarationAnnuelle ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
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
        DeclarationAnnuelle declarationAnnuelle = (DeclarationAnnuelle) o;
        if (declarationAnnuelle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), declarationAnnuelle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeclarationAnnuelle{" +
            "id=" + getId() +
            ", annee=" + getAnnee() +
            ", datePaiement='" + getDatePaiement() + "'" +
            ", numeroQuittance='" + getNumeroQuittance() + "'" +
            ", montantChiffreAffaireHT=" + getMontantChiffreAffaireHT() +
            ", montantChiffreAffaireExport=" + getMontantChiffreAffaireExport() +
            ", montantChiffreAffaireImpot=" + getMontantChiffreAffaireImpot() +
            ", montantChiffreAffaireTTC=" + getMontantChiffreAffaireTTC() +
            ", montantResultatComptable=" + getMontantResultatComptable() +
            ", montantDeductionCommune=" + getMontantDeductionCommune() +
            ", montantAutreDeduction=" + getMontantAutreDeduction() +
            ", montantBaseImposable=" + getMontantBaseImposable() +
            ", montantImpotLiquide=" + getMontantImpotLiquide() +
            ", montantAcompteProvisionnel=" + getMontantAcompteProvisionnel() +
            ", montantRetenueSource=" + getMontantRetenueSource() +
            ", montantNetAPaye=" + getMontantNetAPaye() +
            "}";
    }
}
