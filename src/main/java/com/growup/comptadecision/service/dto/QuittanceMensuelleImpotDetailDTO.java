package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the QuittanceMensuelleImpotDetail entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpotDetailDTO implements Serializable {

    public QuittanceMensuelleImpotDetailDTO() {
    }

    private Long id;

    private String code;

    private String libelle;

    private Boolean parent;

    private Boolean child;

    /**
     * Si true à jouter au montant total la montant total du détail du mois dernier
     */
    private Boolean appliquerReportMontant = Boolean.FALSE;

    /**
     * C'est le coefficient à multiplier par le montant total lors du calcul de la somme des details enfants
     */
    private Float coefficientMontant = 1f;

    private Long impotMensuelId;

    /**
     * Si report, initialiser avec montant total même impot mois préc"dent sinon 0
     */
    private BigDecimal montantReport = BigDecimal.ZERO;

    private BigDecimal montantReportCalc = BigDecimal.ZERO;

    private BigDecimal montantTotal = BigDecimal.ZERO;

    private Long parentQuittanceMensuelleImpotDetailId;

    private String parentQuittanceMensuelleImpotDetailCode;

    private String parentQuittanceMensuelleImpotDetailLibelle;

    private Long quittanceMensuelleImpotId;

    private List<QuittanceMensuelleImpotSousDetailDTO> quittanceMensuelleImpotSousDetails = new ArrayList<>();

    private List<QuittanceMensuelleImpotDetailDTO> childQuittanceMensuelleImpotDetails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuittanceMensuelleImpotId() {
        return quittanceMensuelleImpotId;
    }

    public void setQuittanceMensuelleImpotId(Long quittanceMensuelleImpotId) {
        this.quittanceMensuelleImpotId = quittanceMensuelleImpotId;
    }

    public List<QuittanceMensuelleImpotSousDetailDTO> getQuittanceMensuelleImpotSousDetails() {
        return quittanceMensuelleImpotSousDetails;
    }

    public void setQuittanceMensuelleImpotSousDetails(List<QuittanceMensuelleImpotSousDetailDTO> quittanceMensuelleImpotSousDetails) {
        this.quittanceMensuelleImpotSousDetails = quittanceMensuelleImpotSousDetails;
    }

    public Boolean getParent() {
        return parent;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getParentQuittanceMensuelleImpotDetailId() {
        return parentQuittanceMensuelleImpotDetailId;
    }

    public void setParentQuittanceMensuelleImpotDetailId(Long parentQuittanceMensuelleImpotDetailId) {
        this.parentQuittanceMensuelleImpotDetailId = parentQuittanceMensuelleImpotDetailId;
    }

    public String getParentQuittanceMensuelleImpotDetailLibelle() {
        return parentQuittanceMensuelleImpotDetailLibelle;
    }

    public void setParentQuittanceMensuelleImpotDetailLibelle(String parentQuittanceMensuelleImpotDetailLibelle) {
        this.parentQuittanceMensuelleImpotDetailLibelle = parentQuittanceMensuelleImpotDetailLibelle;
    }

    public List<QuittanceMensuelleImpotDetailDTO> getChildQuittanceMensuelleImpotDetails() {
        return childQuittanceMensuelleImpotDetails;
    }

    public void setChildQuittanceMensuelleImpotDetails(List<QuittanceMensuelleImpotDetailDTO> childQuittanceMensuelleImpotDetails) {
        this.childQuittanceMensuelleImpotDetails = childQuittanceMensuelleImpotDetails;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }


    public Long getImpotMensuelId() {
        return impotMensuelId;
    }

    public void setImpotMensuelId(Long impotMensuelId) {
        this.impotMensuelId = impotMensuelId;
    }

    public BigDecimal getMontantReport() {
        return montantReport;
    }

    public void setMontantReport(BigDecimal montantReport) {
        this.montantReport = montantReport;
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

    public String getParentQuittanceMensuelleImpotDetailCode() {
        return parentQuittanceMensuelleImpotDetailCode;
    }

    public void setParentQuittanceMensuelleImpotDetailCode(String parentQuittanceMensuelleImpotDetailCode) {
        this.parentQuittanceMensuelleImpotDetailCode = parentQuittanceMensuelleImpotDetailCode;
    }

    public BigDecimal getMontantReportCalc() {
        return montantReportCalc;
    }

    public void setMontantReportCalc(BigDecimal montantReportCalc) {
        this.montantReportCalc = montantReportCalc;
    }
}
