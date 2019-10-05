package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A QuittanceMensuelleImpot.
 */
@Entity
@Table(name = "quittance_mensuelle_impot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpot extends AbstractAuditingEntity {

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

    @NotNull
    @Column(name = "type_declaration", nullable = false)
    private TypeDeclaration typeDeclaration;

    @Column(name = "numero_quittance")
    private String numeroQuittance;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(name = "montant_paye", precision = 10, scale = 2)
    private BigDecimal montantPaye;

    @ManyToOne
    @JsonIgnoreProperties("quittanceMensuelleImpots")
    private FicheClient ficheClient;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quittanceMensuelleImpot", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetails = new ArrayList<>();

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

    public List<QuittanceMensuelleImpotDetail> getQuittanceMensuelleImpotDetails() {
        return quittanceMensuelleImpotDetails;
    }

    public void setQuittanceMensuelleImpotDetails(List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetails) {
        this.quittanceMensuelleImpotDetails = quittanceMensuelleImpotDetails;
    }

    public void addQuittanceMensuelleImpotDetail(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail) {
        quittanceMensuelleImpotDetails.add(quittanceMensuelleImpotDetail);
        quittanceMensuelleImpotDetail.setQuittanceMensuelleImpot(this);
    }

    public void removeQuittanceMensuelleImpotDetail(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail) {
        quittanceMensuelleImpotDetails.remove(quittanceMensuelleImpotDetail);
        quittanceMensuelleImpotDetail.setQuittanceMensuelleImpot(null);
    }

    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }



}
