package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A QuittanceMensuelleLigne.
 */
@Entity
@Table(name = "quittance_mensuelle_ligne")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleLigne extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public QuittanceMensuelleLigne() {
    }

    public QuittanceMensuelleLigne(Long id) {
        this.id = id;
    }

    public QuittanceMensuelleLigne(QuittanceMensuelle quittanceMensuelle, ImpotMensuel impotMensuel) {
        this.quittanceMensuelle = quittanceMensuelle;
        this.impotMensuel = impotMensuel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "parent")
    private Boolean parent = Boolean.FALSE;

    @Column(name = "child")
    private Boolean child = Boolean.FALSE;

    @OneToMany(mappedBy = "parentQuittanceMensuelleLigne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuittanceMensuelleLigne> childQuittanceMensuelleLignes = new ArrayList<>();

    /**
     * Si true à jouter au montant total la montant total du détail du mois dernier
     */
    @Column(name = "appliquer_report_montant")
    private Boolean appliquerReportMontant = Boolean.FALSE;

    @Column(name = "montant_report")
    private BigDecimal montantReport = BigDecimal.ZERO;

    /**
     * C'est le coefficient à multiplier par le montant total lors du calcul de la somme des details enfants
     */
    @Column(name = "coefficient_montant")
    private Float coefficientMontant = 1f;


    @ManyToOne(fetch = FetchType.LAZY)
    private QuittanceMensuelleLigne parentQuittanceMensuelleLigne;

    @Column(name = "montant_total")
    private BigDecimal montantTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("impotMensuelClientLignes")
    private QuittanceMensuelle quittanceMensuelle;

    @ManyToOne(fetch = FetchType.LAZY)
    private ImpotMensuel impotMensuel;

    @OneToMany(mappedBy = "quittanceMensuelleLigne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuittanceMensuelleSousLigne> quittanceMensuelleSousLignes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public QuittanceMensuelle getQuittanceMensuelle() {
        return quittanceMensuelle;
    }

    public void setQuittanceMensuelle(QuittanceMensuelle quittanceMensuelle) {
        this.quittanceMensuelle = quittanceMensuelle;
    }

    public ImpotMensuel getImpotMensuel() {
        return impotMensuel;
    }

    public void setImpotMensuel(ImpotMensuel impotMensuel) {
        this.impotMensuel = impotMensuel;
    }

    public List<QuittanceMensuelleSousLigne> getQuittanceMensuelleSousLignes() {
        return quittanceMensuelleSousLignes;
    }

    public void setQuittanceMensuelleSousLignes(List<QuittanceMensuelleSousLigne> quittanceMensuelleSousLignes) {
        this.quittanceMensuelleSousLignes = quittanceMensuelleSousLignes;
    }

    public Boolean getParent() {
        return parent;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public Boolean getChild() {
        return child;
    }

    public void setChild(Boolean child) {
        this.child = child;
    }

    public List<QuittanceMensuelleLigne> getChildQuittanceMensuelleLignes() {
        return childQuittanceMensuelleLignes;
    }

    public void setChildQuittanceMensuelleLignes(List<QuittanceMensuelleLigne> childQuittanceMensuelleLignes) {
        this.childQuittanceMensuelleLignes = childQuittanceMensuelleLignes;
    }

    public QuittanceMensuelleLigne getParentQuittanceMensuelleLigne() {
        return parentQuittanceMensuelleLigne;
    }

    public void setParentQuittanceMensuelleLigne(QuittanceMensuelleLigne parentQuittanceMensuelleLigne) {
        this.parentQuittanceMensuelleLigne = parentQuittanceMensuelleLigne;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getAppliquerReportMontant() {
        return appliquerReportMontant;
    }

    public void setAppliquerReportMontant(Boolean appliquerReportMontant) {
        this.appliquerReportMontant = appliquerReportMontant;
    }

    public Float getCoefficientMontant() {
        return coefficientMontant;
    }

    public void setCoefficientMontant(Float coefficientMontant) {
        this.coefficientMontant = coefficientMontant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMontantReport() {
        return montantReport;
    }

    public void setMontantReport(BigDecimal montantReport) {
        this.montantReport = montantReport;
    }
}

