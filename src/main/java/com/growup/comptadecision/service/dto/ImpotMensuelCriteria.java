package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the ImpotMensuel entity. This class is used in ImpotMensuelResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /impot-mensuels?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ImpotMensuelCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter libelle;

    private StringFilter description;

    private BooleanFilter parent;

    private BooleanFilter child;

    private BooleanFilter appliquerReportMontant;

    private FloatFilter coefficientMontant;

    private LongFilter impotMensuelLigneId;

    private LongFilter childImpotMensuelId;

    private LongFilter parentImpotMensuelId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public FloatFilter getCoefficientMontant() {
        return coefficientMontant;
    }

    public void setCoefficientMontant(FloatFilter coefficientMontant) {
        this.coefficientMontant = coefficientMontant;
    }

    public LongFilter getImpotMensuelLigneId() {
        return impotMensuelLigneId;
    }

    public void setImpotMensuelLigneId(LongFilter impotMensuelLigneId) {
        this.impotMensuelLigneId = impotMensuelLigneId;
    }

    public LongFilter getChildImpotMensuelId() {
        return childImpotMensuelId;
    }

    public void setChildImpotMensuelId(LongFilter childImpotMensuelId) {
        this.childImpotMensuelId = childImpotMensuelId;
    }

    public LongFilter getParentImpotMensuelId() {
        return parentImpotMensuelId;
    }

    public void setParentImpotMensuelId(LongFilter parentImpotMensuelId) {
        this.parentImpotMensuelId = parentImpotMensuelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImpotMensuelCriteria that = (ImpotMensuelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(parent, that.parent) &&
            Objects.equals(child, that.child) &&
            Objects.equals(appliquerReportMontant, that.appliquerReportMontant) &&
            Objects.equals(coefficientMontant, that.coefficientMontant) &&
            Objects.equals(impotMensuelLigneId, that.impotMensuelLigneId) &&
            Objects.equals(childImpotMensuelId, that.childImpotMensuelId) &&
            Objects.equals(parentImpotMensuelId, that.parentImpotMensuelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        description,
        parent,
        child,
        appliquerReportMontant,
        coefficientMontant,
        impotMensuelLigneId,
        childImpotMensuelId,
        parentImpotMensuelId
        );
    }

    @Override
    public String toString() {
        return "ImpotMensuelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (parent != null ? "parent=" + parent + ", " : "") +
                (child != null ? "child=" + child + ", " : "") +
                (appliquerReportMontant != null ? "appliquerReportMontant=" + appliquerReportMontant + ", " : "") +
                (coefficientMontant != null ? "coefficientMontant=" + coefficientMontant + ", " : "") +
                (impotMensuelLigneId != null ? "impotMensuelLigneId=" + impotMensuelLigneId + ", " : "") +
                (childImpotMensuelId != null ? "childImpotMensuelId=" + childImpotMensuelId + ", " : "") +
                (parentImpotMensuelId != null ? "parentImpotMensuelId=" + parentImpotMensuelId + ", " : "") +
            "}";
    }

}
