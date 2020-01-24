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
 * A QuittanceMensuelleImpotDetail.
 */
@Entity
@Table(name = "quittance_mensuelle_impot_detail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpotDetail extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public QuittanceMensuelleImpotDetail() {
    }

    public QuittanceMensuelleImpotDetail(Long id) {
        this.id = id;
    }

    public QuittanceMensuelleImpotDetail(QuittanceMensuelleImpot quittanceMensuelleImpot, ImpotMensuel impotMensuel) {
        this.quittanceMensuelleImpot = quittanceMensuelleImpot;
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

    @Column(name = "parent_quittance_mensuelle_impot_detail_id")
    @OneToMany(mappedBy = "parentQuittanceMensuelleImpotDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuittanceMensuelleImpotDetail> childQuittanceMensuelleImpotDetails = new ArrayList<>();

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
    private QuittanceMensuelleImpotDetail parentQuittanceMensuelleImpotDetail;

    @Column(name = "montant_total")
    private BigDecimal montantTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("impotMensuelClientDetails")
    private QuittanceMensuelleImpot quittanceMensuelleImpot;

    @ManyToOne(fetch = FetchType.LAZY)
    private ImpotMensuel impotMensuel;

    @OneToMany(mappedBy = "quittanceMensuelleImpotDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuittanceMensuelleImpotSousDetail> quittanceMensuelleImpotSousDetails = new ArrayList<>();

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

    public QuittanceMensuelleImpot getQuittanceMensuelleImpot() {
        return quittanceMensuelleImpot;
    }

    public void setQuittanceMensuelleImpot(QuittanceMensuelleImpot quittanceMensuelleImpot) {
        this.quittanceMensuelleImpot = quittanceMensuelleImpot;
    }

    public ImpotMensuel getImpotMensuel() {
        return impotMensuel;
    }

    public void setImpotMensuel(ImpotMensuel impotMensuel) {
        this.impotMensuel = impotMensuel;
    }

    public List<QuittanceMensuelleImpotSousDetail> getQuittanceMensuelleImpotSousDetails() {
        return quittanceMensuelleImpotSousDetails;
    }

    public void setQuittanceMensuelleImpotSousDetails(List<QuittanceMensuelleImpotSousDetail> quittanceMensuelleImpotSousDetails) {
        this.quittanceMensuelleImpotSousDetails = quittanceMensuelleImpotSousDetails;
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

    public List<QuittanceMensuelleImpotDetail> getChildQuittanceMensuelleImpotDetails() {
        return childQuittanceMensuelleImpotDetails;
    }

    public void setChildQuittanceMensuelleImpotDetails(List<QuittanceMensuelleImpotDetail> childQuittanceMensuelleImpotDetails) {
        this.childQuittanceMensuelleImpotDetails = childQuittanceMensuelleImpotDetails;
    }

    public QuittanceMensuelleImpotDetail getParentQuittanceMensuelleImpotDetail() {
        return parentQuittanceMensuelleImpotDetail;
    }

    public void setParentQuittanceMensuelleImpotDetail(QuittanceMensuelleImpotDetail parentQuittanceMensuelleImpotDetail) {
        this.parentQuittanceMensuelleImpotDetail = parentQuittanceMensuelleImpotDetail;
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

