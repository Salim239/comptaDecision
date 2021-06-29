package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the QuittanceMensuelleLigne entity. This class is used in QuittanceMensuelleLigneResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quittance-mensuelle-lignes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuittanceMensuelleLigneCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BigDecimalFilter montantTotal;

    private StringFilter code;

    private StringFilter libelle;

    private StringFilter description;

    private BooleanFilter parent;

    private BooleanFilter child;

    private BooleanFilter appliquerReportMontant;

    private BigDecimalFilter montantReport;

    private FloatFilter coefficientMontant;

    private LongFilter quittanceMensuelleId;

    private LongFilter quittanceMensuelleSousLigneId;

    private LongFilter parentQuittanceMensuelleLigneId;

    private LongFilter impotMensuelId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public BigDecimalFilter getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimalFilter montantTotal) {
        this.montantTotal = montantTotal;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public BooleanFilter getParent() {
        return parent;
    }

    public void setParent(BooleanFilter parent) {
        this.parent = parent;
    }

    public BooleanFilter getChild() {
        return child;
    }

    public void setChild(BooleanFilter child) {
        this.child = child;
    }

    public BooleanFilter getAppliquerReportMontant() {
        return appliquerReportMontant;
    }

    public void setAppliquerReportMontant(BooleanFilter appliquerReportMontant) {
        this.appliquerReportMontant = appliquerReportMontant;
    }

    public BigDecimalFilter getMontantReport() {
        return montantReport;
    }

    public void setMontantReport(BigDecimalFilter montantReport) {
        this.montantReport = montantReport;
    }

    public FloatFilter getCoefficientMontant() {
        return coefficientMontant;
    }

    public void setCoefficientMontant(FloatFilter coefficientMontant) {
        this.coefficientMontant = coefficientMontant;
    }

    public LongFilter getQuittanceMensuelleId() {
        return quittanceMensuelleId;
    }

    public void setQuittanceMensuelleId(LongFilter quittanceMensuelleId) {
        this.quittanceMensuelleId = quittanceMensuelleId;
    }

    public LongFilter getQuittanceMensuelleSousLigneId() {
        return quittanceMensuelleSousLigneId;
    }

    public void setQuittanceMensuelleSousLigneId(LongFilter quittanceMensuelleSousLigneId) {
        this.quittanceMensuelleSousLigneId = quittanceMensuelleSousLigneId;
    }

    public LongFilter getParentQuittanceMensuelleLigneId() {
        return parentQuittanceMensuelleLigneId;
    }

    public void setParentQuittanceMensuelleLigneId(LongFilter parentQuittanceMensuelleLigneId) {
        this.parentQuittanceMensuelleLigneId = parentQuittanceMensuelleLigneId;
    }

    public LongFilter getImpotMensuelId() {
        return impotMensuelId;
    }

    public void setImpotMensuelId(LongFilter impotMensuelId) {
        this.impotMensuelId = impotMensuelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QuittanceMensuelleLigneCriteria that = (QuittanceMensuelleLigneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(montantTotal, that.montantTotal) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(parent, that.parent) &&
            Objects.equals(child, that.child) &&
            Objects.equals(appliquerReportMontant, that.appliquerReportMontant) &&
            Objects.equals(montantReport, that.montantReport) &&
            Objects.equals(coefficientMontant, that.coefficientMontant) &&
            Objects.equals(quittanceMensuelleId, that.quittanceMensuelleId) &&
            Objects.equals(quittanceMensuelleSousLigneId, that.quittanceMensuelleSousLigneId) &&
            Objects.equals(parentQuittanceMensuelleLigneId, that.parentQuittanceMensuelleLigneId) &&
            Objects.equals(impotMensuelId, that.impotMensuelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        montantTotal,
        code,
        libelle,
        description,
        parent,
        child,
        appliquerReportMontant,
        montantReport,
        coefficientMontant,
        quittanceMensuelleId,
        quittanceMensuelleSousLigneId,
        parentQuittanceMensuelleLigneId,
        impotMensuelId
        );
    }

    @Override
    public String toString() {
        return "QuittanceMensuelleLigneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (montantTotal != null ? "montantTotal=" + montantTotal + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (parent != null ? "parent=" + parent + ", " : "") +
                (child != null ? "child=" + child + ", " : "") +
                (appliquerReportMontant != null ? "appliquerReportMontant=" + appliquerReportMontant + ", " : "") +
                (montantReport != null ? "montantReport=" + montantReport + ", " : "") +
                (coefficientMontant != null ? "coefficientMontant=" + coefficientMontant + ", " : "") +
                (quittanceMensuelleId != null ? "quittanceMensuelleId=" + quittanceMensuelleId + ", " : "") +
                (quittanceMensuelleSousLigneId != null ? "quittanceMensuelleSousLigneId=" + quittanceMensuelleSousLigneId + ", " : "") +
                (parentQuittanceMensuelleLigneId != null ? "parentQuittanceMensuelleLigneId=" + parentQuittanceMensuelleLigneId + ", " : "") +
                (impotMensuelId != null ? "impotMensuelId=" + impotMensuelId + ", " : "") +
            "}";
    }

}
