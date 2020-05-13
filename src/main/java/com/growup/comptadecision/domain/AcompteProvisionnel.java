package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A AcompteProvisionnel.
 */
@Entity
@Table(name = "acompte_provisionnel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class AcompteProvisionnel extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "numero_quittance")
    private String numeroQuittance;

    @Column(name = "montant_base", precision = 20, scale = 6)
    private BigDecimal montantBase;

    @Column(name = "montant_acompte_provisionnel", precision = 20, scale = 6)
    private BigDecimal montantAcompteProvisionnel;

    @Column(name = "montant_report_anterieur", precision = 20, scale = 6)
    private BigDecimal montantReportAnterieur;

    @Column(name = "montant_retenue_source", precision = 20, scale = 6)
    private BigDecimal montantRetenueSource;

    @Column(name = "montant_net", precision = 20, scale = 6)
    private BigDecimal montantNet;

    @ManyToOne
    @JsonIgnoreProperties("acompteProvisionnels")
    private FicheClient ficheClient;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeDeclaration type = TypeDeclaration.DECLARATION_INITIALE;

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private StatutDeclaration statut = StatutDeclaration.BROUILLON;

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

    public AcompteProvisionnel annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getNumero() {
        return numero;
    }

    public AcompteProvisionnel numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public AcompteProvisionnel date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public AcompteProvisionnel numeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
        return this;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimal getMontantBase() {
        return montantBase;
    }

    public AcompteProvisionnel montantBase(BigDecimal montantBase) {
        this.montantBase = montantBase;
        return this;
    }

    public void setMontantBase(BigDecimal montantBase) {
        this.montantBase = montantBase;
    }

    public BigDecimal getMontantAcompteProvisionnel() {
        return montantAcompteProvisionnel;
    }

    public AcompteProvisionnel montantAcompteProvisionnel(BigDecimal montantAcompteProvisionnel) {
        this.montantAcompteProvisionnel = montantAcompteProvisionnel;
        return this;
    }

    public void setMontantAcompteProvisionnel(BigDecimal montantAcompteProvisionnel) {
        this.montantAcompteProvisionnel = montantAcompteProvisionnel;
    }

    public BigDecimal getMontantReportAnterieur() {
        return montantReportAnterieur;
    }

    public AcompteProvisionnel montantReportAnterieur(BigDecimal montantReportAnterieur) {
        this.montantReportAnterieur = montantReportAnterieur;
        return this;
    }

    public void setMontantReportAnterieur(BigDecimal montantReportAnterieur) {
        this.montantReportAnterieur = montantReportAnterieur;
    }

    public BigDecimal getMontantRetenueSource() {
        return montantRetenueSource;
    }

    public AcompteProvisionnel montantRetenueSource(BigDecimal montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
        return this;
    }

    public void setMontantRetenueSource(BigDecimal montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
    }

    public BigDecimal getMontantNet() {
        return montantNet;
    }

    public AcompteProvisionnel montantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
        return this;
    }

    public void setMontantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public AcompteProvisionnel ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public StatutDeclaration getStatut() {
        return statut;
    }

    public void setStatut(StatutDeclaration statut) {
        this.statut = statut;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public TypeDeclaration getType() {
        return type;
    }

    public void setType(TypeDeclaration type) {
        this.type = type;
    }
}
