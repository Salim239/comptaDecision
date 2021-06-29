package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.ModePaiement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Paiement.
 */
@Entity
@Table(name = "paiement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    /**
     * cheque, virement, caisse, ...
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mode_paiement", nullable = false)
    private ModePaiement modePaiement;

    /**
     * num cheque, virement, operation caisse ...
     */
    @NotNull
    @Column(name = "numero_preuve_paiement", nullable = false)
    private String numeroPreuvePaiement;

    /**
     * date cheque, date virement
     */
    @NotNull
    @Column(name = "date_paiement", nullable = false)
    private LocalDate datePaiement;

    @NotNull
    @Column(name = "montant_paye", nullable = false)
    private BigDecimal montantPaye;

    @NotNull
    @Column(name = "montant_du", nullable = false)
    private BigDecimal montantDu;

    @OneToOne
    @JoinColumn(unique = true)
    private LigneCaisse ligneCaisse;

    @ManyToOne
    @JsonIgnoreProperties("paiements")
    private FicheClient ficheClient;

    @ManyToOne
    @JsonIgnoreProperties("paiements")
    private Cnss cnss;

    @ManyToOne
    @JsonIgnoreProperties("paiements")
    private QuittanceMensuelle quittanceMensuelle;

    @ManyToOne
    @JsonIgnoreProperties("paiements")
    private DeclarationAnnuelle declarationAnnuelle;

    @ManyToOne
    @JsonIgnoreProperties("paiements")
    private DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle;

    @ManyToOne
    @JsonIgnoreProperties("paiements")
    private AcompteProvisionnel acompteProvisionnel;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Paiement libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public Paiement modePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
        return this;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getNumeroPreuvePaiement() {
        return numeroPreuvePaiement;
    }

    public Paiement numeroPreuvePaiement(String numeroPreuvePaiement) {
        this.numeroPreuvePaiement = numeroPreuvePaiement;
        return this;
    }

    public void setNumeroPreuvePaiement(String numeroPreuvePaiement) {
        this.numeroPreuvePaiement = numeroPreuvePaiement;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public Paiement datePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
        return this;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public Paiement montantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
        return this;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public BigDecimal getMontantDu() {
        return montantDu;
    }

    public Paiement montantDu(BigDecimal montantDu) {
        this.montantDu = montantDu;
        return this;
    }

    public void setMontantDu(BigDecimal montantDu) {
        this.montantDu = montantDu;
    }

    public LigneCaisse getLigneCaisse() {
        return ligneCaisse;
    }

    public Paiement ligneCaisse(LigneCaisse ligneCaisse) {
        this.ligneCaisse = ligneCaisse;
        return this;
    }

    public void setLigneCaisse(LigneCaisse ligneCaisse) {
        this.ligneCaisse = ligneCaisse;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public Paiement ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }

    public Cnss getCnss() {
        return cnss;
    }

    public Paiement cnss(Cnss cnss) {
        this.cnss = cnss;
        return this;
    }

    public void setCnss(Cnss cnss) {
        this.cnss = cnss;
    }

    public QuittanceMensuelle getQuittanceMensuelle() {
        return quittanceMensuelle;
    }

    public Paiement quittanceMensuelle(QuittanceMensuelle quittanceMensuelle) {
        this.quittanceMensuelle = quittanceMensuelle;
        return this;
    }

    public void setQuittanceMensuelle(QuittanceMensuelle quittanceMensuelle) {
        this.quittanceMensuelle = quittanceMensuelle;
    }

    public DeclarationAnnuelle getDeclarationAnnuelle() {
        return declarationAnnuelle;
    }

    public Paiement declarationAnnuelle(DeclarationAnnuelle declarationAnnuelle) {
        this.declarationAnnuelle = declarationAnnuelle;
        return this;
    }

    public void setDeclarationAnnuelle(DeclarationAnnuelle declarationAnnuelle) {
        this.declarationAnnuelle = declarationAnnuelle;
    }

    public DeclarationEmployeurAnnuelle getDeclarationEmployeurAnnuelle() {
        return declarationEmployeurAnnuelle;
    }

    public Paiement declarationEmployeurAnnuelle(DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle) {
        this.declarationEmployeurAnnuelle = declarationEmployeurAnnuelle;
        return this;
    }

    public void setDeclarationEmployeurAnnuelle(DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle) {
        this.declarationEmployeurAnnuelle = declarationEmployeurAnnuelle;
    }

    public AcompteProvisionnel getAcompteProvisionnel() {
        return acompteProvisionnel;
    }

    public Paiement acompteProvisionnel(AcompteProvisionnel acompteProvisionnel) {
        this.acompteProvisionnel = acompteProvisionnel;
        return this;
    }

    public void setAcompteProvisionnel(AcompteProvisionnel acompteProvisionnel) {
        this.acompteProvisionnel = acompteProvisionnel;
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
        Paiement paiement = (Paiement) o;
        if (paiement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paiement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Paiement{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", modePaiement='" + getModePaiement() + "'" +
            ", numeroPreuvePaiement='" + getNumeroPreuvePaiement() + "'" +
            ", datePaiement='" + getDatePaiement() + "'" +
            ", montantPaye=" + getMontantPaye() +
            ", montantDu=" + getMontantDu() +
            "}";
    }
}
