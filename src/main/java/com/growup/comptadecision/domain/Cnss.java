package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.TypeCnss;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A Cnss.
 */
@Entity
@Table(name = "cnss")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class Cnss extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "type_cnss", nullable = false)
    private TypeCnss typeCnss;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @NotNull
    @Column(name = "trimestre", nullable = false)
    private Integer trimestre;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "numero_quittance")
    private String numeroQuittance;

    @Column(name = "montant_salaire_brut_normal", precision = 10, scale = 2)
    private BigDecimal montantSalaireBrutNormal;

    @Column(name = "montant_salaire_brut_karama", precision = 10, scale = 2)
    private BigDecimal montantSalaireBrutKarama;

    @Column(name = "montant_salaire_brut_autre", precision = 10, scale = 2)
    private BigDecimal montantSalaireBrutAutre;

    @Column(name = "montantTotal", precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(name = "cnss", precision = 10, scale = 2)
    private BigDecimal cnss;

    @ManyToOne
    @JsonIgnoreProperties("cnsses")
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

    public Cnss annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getTrimestre() {
        return trimestre;
    }

    public Cnss trimestre(Integer trimestre) {
        this.trimestre = trimestre;
        return this;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public LocalDate getDate() {
        return date;
    }

    public Cnss date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public Cnss numeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
        return this;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimal getMontantSalaireBrutNormal() {
        return montantSalaireBrutNormal;
    }

    public Cnss montantSalaireBrutNormal(BigDecimal montantSalaireBrutNormal) {
        this.montantSalaireBrutNormal = montantSalaireBrutNormal;
        return this;
    }

    public void setMontantSalaireBrutNormal(BigDecimal montantSalaireBrutNormal) {
        this.montantSalaireBrutNormal = montantSalaireBrutNormal;
    }

    public BigDecimal getMontantSalaireBrutKarama() {
        return montantSalaireBrutKarama;
    }

    public Cnss montantSalaireBrutKarama(BigDecimal montantSalaireBrutKarama) {
        this.montantSalaireBrutKarama = montantSalaireBrutKarama;
        return this;
    }

    public void setMontantSalaireBrutKarama(BigDecimal montantSalaireBrutKarama) {
        this.montantSalaireBrutKarama = montantSalaireBrutKarama;
    }

    public BigDecimal getMontantSalaireBrutAutre() {
        return montantSalaireBrutAutre;
    }

    public Cnss montantSalaireBrutAutre(BigDecimal montantSalaireBrutAutre) {
        this.montantSalaireBrutAutre = montantSalaireBrutAutre;
        return this;
    }

    public void setMontantSalaireBrutAutre(BigDecimal montantSalaireBrutAutre) {
        this.montantSalaireBrutAutre = montantSalaireBrutAutre;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public Cnss tot(BigDecimal tot) {
        this.montantTotal = tot;
        return this;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getCnss() {
        return cnss;
    }

    public Cnss cnss(BigDecimal cnss) {
        this.cnss = cnss;
        return this;
    }

    public void setCnss(BigDecimal cnss) {
        this.cnss = cnss;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public Cnss ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }

    public TypeCnss getTypeCnss() {
        return typeCnss;
    }

    public void setTypeCnss(TypeCnss typeCnss) {
        this.typeCnss = typeCnss;
    }

}
