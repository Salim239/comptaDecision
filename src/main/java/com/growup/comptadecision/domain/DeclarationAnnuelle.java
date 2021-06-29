package com.growup.comptadecision.domain;


import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
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
 * A DeclarationAnnuelle.
 */
@Entity
@Table(name = "declaration_annuelle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class DeclarationAnnuelle extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public DeclarationAnnuelle() {
    }

    public DeclarationAnnuelle(FicheClient ficheClient, @NotNull Integer annee, @NotNull TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
        this.annee = annee;
        this.ficheClient = ficheClient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_declaration", nullable = false)
    private TypeDeclaration typeDeclaration = TypeDeclaration.DECLARATION_INITIALE;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(name = "numero")
    private String numeroQuittance;

    @Column(name = "montant_impot_annuel")
    private BigDecimal montantImpotAnnuel = BigDecimal.ZERO;

    @Column(name = "montant_ap_payes")
    private BigDecimal montantApPayes = BigDecimal.ZERO;

    @Column(name = "montant_retenue_source")
    private BigDecimal montantRetenueSource = BigDecimal.ZERO;

    @Column(name = "montant_report_anterieur")
    private BigDecimal montantReportAnterieur = BigDecimal.ZERO;

    @Column(name = "montant_net")
    private BigDecimal montantNet = BigDecimal.ZERO;

    @Column(name = "montant_penalite")
    private BigDecimal montantPenalite = BigDecimal.ZERO;


    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private StatutDeclaration statut = StatutDeclaration.BROUILLON;

    @OneToMany(mappedBy = "declarationAnnuelle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeclarationAnnuelleLigne> declarationAnnuelleLignes = new ArrayList<>();

    @ManyToOne
    private FicheClient ficheClient;

    @Override
    public String toString() {
        return "DeclarationAnnuelle{" +
            "id=" + id +
            ", typeDeclaration=" + typeDeclaration +
            ", annee=" + annee +
            ", datePaiement=" + datePaiement +
            ", numeroQuittance='" + numeroQuittance + '\'' +
            ", montantImpotAnnuel=" + montantImpotAnnuel +
            ", montantApPayes=" + montantApPayes +
            ", montantRetenueSource=" + montantRetenueSource +
            ", montantReportAnterieur=" + montantReportAnterieur +
            ", montantNet=" + montantNet +
            ", montantPenalite=" + montantPenalite +
            ", statut=" + statut +
            ", declarationAnnuelleLignes=" + declarationAnnuelleLignes +
            ", ficheClient=" + ficheClient +
            '}';
    }

    public BigDecimal getMontantPenalite() {
        return montantPenalite;
    }

    public void setMontantPenalite(BigDecimal montantPenalite) {
        this.montantPenalite = montantPenalite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimal getMontantImpotAnnuel() {
        return montantImpotAnnuel;
    }

    public void setMontantImpotAnnuel(BigDecimal montantImpotAnnuel) {
        this.montantImpotAnnuel = montantImpotAnnuel;
    }

    public BigDecimal getMontantApPayes() {
        return montantApPayes;
    }

    public void setMontantApPayes(BigDecimal montantApPayes) {
        this.montantApPayes = montantApPayes;
    }

    public BigDecimal getMontantRetenueSource() {
        return montantRetenueSource;
    }

    public void setMontantRetenueSource(BigDecimal montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
    }

    public BigDecimal getMontantReportAnterieur() {
        return montantReportAnterieur;
    }

    public void setMontantReportAnterieur(BigDecimal montantReportAnterieur) {
        this.montantReportAnterieur = montantReportAnterieur;
    }

    public BigDecimal getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
    }

    public StatutDeclaration getStatut() {
        return statut;
    }

    public void setStatut(StatutDeclaration statut) {
        this.statut = statut;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }

    public List<DeclarationAnnuelleLigne> getDeclarationAnnuelleLignes() {
        return declarationAnnuelleLignes;
    }

    public void setDeclarationAnnuelleLignes(List<DeclarationAnnuelleLigne> declarationAnnuelleLignes) {
        this.declarationAnnuelleLignes = declarationAnnuelleLignes;
    }
}
