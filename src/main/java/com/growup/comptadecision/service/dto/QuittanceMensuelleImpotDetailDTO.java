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
    private Boolean appliquerReportMontant;

    /**
     * C'est le coefficient à multiplier par le montant total lors du calcul de la somme des details enfants
     */
    private Float coefficientMontant;

    private Long impotMensuelId;

    private String impotMensuelLibelle;

    /**
     * Si true à jouter au montant total la montant total du détail du mois dernier
     */
    private Boolean impotMensuelAppliquerReportMontant = Boolean.FALSE;

    /**
     * Si report, initialiser avec montant total même impot mois préc"dent sinon 0
     */
    private BigDecimal montantTotalReport = BigDecimal.ZERO;

    /**
     * C'est le coefficient à multiplier par le montant total lors du calcul de la somme des details enfants
     */
    private Float impotMensuelCoefficientMontant = 1f;

    private BigDecimal montantTotal;

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

    public Boolean getImpotMensuelAppliquerReportMontant() {
        return impotMensuelAppliquerReportMontant;
    }

    public void setImpotMensuelAppliquerReportMontant(Boolean impotMensuelAppliquerReportMontant) {
        this.impotMensuelAppliquerReportMontant = impotMensuelAppliquerReportMontant;
    }

    public Float getImpotMensuelCoefficientMontant() {
        return impotMensuelCoefficientMontant;
    }

    public void setImpotMensuelCoefficientMontant(Float impotMensuelCoefficientMontant) {
        this.impotMensuelCoefficientMontant = impotMensuelCoefficientMontant;
    }

    public Long getImpotMensuelId() {
        return impotMensuelId;
    }

    public void setImpotMensuelId(Long impotMensuelId) {
        this.impotMensuelId = impotMensuelId;
    }

    public String getImpotMensuelLibelle() {
        return impotMensuelLibelle;
    }

    public void setImpotMensuelLibelle(String impotMensuelLibelle) {
        this.impotMensuelLibelle = impotMensuelLibelle;
    }

    public BigDecimal getMontantTotalReport() {
        return montantTotalReport;
    }

    public void setMontantTotalReport(BigDecimal montantTotalReport) {
        this.montantTotalReport = montantTotalReport;
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
}
