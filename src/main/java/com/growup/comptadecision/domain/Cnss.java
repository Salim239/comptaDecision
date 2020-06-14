package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.TypeCnss;
import com.growup.comptadecision.domain.enumeration.TypeDeclarationCnss;
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
 * A Cnss.
 */
@Entity
@Table(name = "cnss")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@NoArgsConstructor
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

    @Column(name = "type_declaration", nullable = false)
    private TypeDeclarationCnss typeDeclaration = TypeDeclarationCnss.DECLARATION_INITALE;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "numero_quittance")
    private String numeroQuittance;

    @Column(name = "montant_salaire_brut_normal")
    private BigDecimal montantSalaireBrutNormal;

    @Column(name = "montant_salaire_brut_karama")
    private BigDecimal montantSalaireBrutKarama;

    @Column(name = "taux_cnss_normal")
    private BigDecimal tauxCnssNormal;

    @Column(name = "taux_cnss_karama")
    private BigDecimal tauxCnssKarama;

    @Column(name = "taux_cnss_accident")
    private BigDecimal tauxCnssNormalAccident;    
    
    @Column(name = "taux_cnss_accident_karama")
    private BigDecimal tauxCnssKaramaAccident;

    @Column(name = "montant_cnss_normal")
    private BigDecimal montantCnssNormal;

    @Column(name = "montant_cnss_karama")
    private BigDecimal montantCnssKarama;

    @Column(name = "montant_total_cnss")
    private BigDecimal montantTotalCnss;

    @Column(name = "montant_total_salaire_brut")
    private BigDecimal montantTotalSalaireBrut;

    @ManyToOne
    @JsonIgnoreProperties("cnss")
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

    

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public Cnss ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public BigDecimal getTauxCnssKaramaAccident() {
        return tauxCnssKaramaAccident;
    }

    public void setTauxCnssKaramaAccident(BigDecimal tauxCnssKaramaAccident) {
        this.tauxCnssKaramaAccident = tauxCnssKaramaAccident;
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

    public TypeDeclarationCnss getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclarationCnss typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public BigDecimal getTauxCnssNormal() {
        return tauxCnssNormal;
    }

    public void setTauxCnssNormal(BigDecimal tauxCnssNormal) {
        this.tauxCnssNormal = tauxCnssNormal;
    }

    public BigDecimal getTauxCnssKarama() {
        return tauxCnssKarama;
    }

    public void setTauxCnssKarama(BigDecimal tauxCnssKarama) {
        this.tauxCnssKarama = tauxCnssKarama;
    }

    public BigDecimal getTauxCnssNormalAccident() {
        return tauxCnssNormalAccident;
    }

    public void setTauxCnssNormalAccident(BigDecimal tauxCnssNormalAccident) {
        this.tauxCnssNormalAccident = tauxCnssNormalAccident;
    }

    public BigDecimal getMontantCnssNormal() {
        return montantCnssNormal;
    }

    public void setMontantCnssNormal(BigDecimal montantCnssNormal) {
        this.montantCnssNormal = montantCnssNormal;
    }

    public BigDecimal getMontantCnssKarama() {
        return montantCnssKarama;
    }

    public void setMontantCnssKarama(BigDecimal montantCnssKarama) {
        this.montantCnssKarama = montantCnssKarama;
    }

    public BigDecimal getMontantTotalCnss() {
        return montantTotalCnss;
    }

    public void setMontantTotalCnss(BigDecimal montantTotalCnss) {
        this.montantTotalCnss = montantTotalCnss;
    }

    public BigDecimal getMontantTotalSalaireBrut() {
        return montantTotalSalaireBrut;
    }

    public void setMontantTotalSalaireBrut(BigDecimal montantTotalSalaireBrut) {
        this.montantTotalSalaireBrut = montantTotalSalaireBrut;
    }
}
