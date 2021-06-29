package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the DeclarationAnnuelleLigne entity. This class is used in DeclarationAnnuelleLigneResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /decalration-annuelle-lignes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DeclarationAnnuelleLigneCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private IntegerFilter triOrdre;

    private BooleanFilter calcule;

    private BigDecimalFilter montant;

    private BigDecimalFilter montantCalcule;

    private StringFilter libelle;

    private StringFilter description;

    private LongFilter impotAnnuelId;

    private LongFilter declarationAnnuelleId;

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

    public IntegerFilter getTriOrdre() {
        return triOrdre;
    }

    public void setTriOrdre(IntegerFilter triOrdre) {
        this.triOrdre = triOrdre;
    }

    public BooleanFilter getCalcule() {
        return calcule;
    }

    public void setCalcule(BooleanFilter calcule) {
        this.calcule = calcule;
    }

    public BigDecimalFilter getMontant() {
        return montant;
    }

    public void setMontant(BigDecimalFilter montant) {
        this.montant = montant;
    }

    public BigDecimalFilter getMontantCalcule() {
        return montantCalcule;
    }

    public void setMontantCalcule(BigDecimalFilter montantCalcule) {
        this.montantCalcule = montantCalcule;
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

    public LongFilter getImpotAnnuelId() {
        return impotAnnuelId;
    }

    public void setImpotAnnuelId(LongFilter impotAnnuelId) {
        this.impotAnnuelId = impotAnnuelId;
    }

    public LongFilter getDeclarationAnnuelleId() {
        return declarationAnnuelleId;
    }

    public void setDeclarationAnnuelleId(LongFilter declarationAnnuelleId) {
        this.declarationAnnuelleId = declarationAnnuelleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DeclarationAnnuelleLigneCriteria that = (DeclarationAnnuelleLigneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(triOrdre, that.triOrdre) &&
            Objects.equals(calcule, that.calcule) &&
            Objects.equals(montant, that.montant) &&
            Objects.equals(montantCalcule, that.montantCalcule) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(impotAnnuelId, that.impotAnnuelId) &&
            Objects.equals(declarationAnnuelleId, that.declarationAnnuelleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        triOrdre,
        calcule,
        montant,
        montantCalcule,
        libelle,
        description,
        impotAnnuelId,
        declarationAnnuelleId
        );
    }

    @Override
    public String toString() {
        return "DeclarationAnnuelleLigneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (triOrdre != null ? "triOrdre=" + triOrdre + ", " : "") +
                (calcule != null ? "calcule=" + calcule + ", " : "") +
                (montant != null ? "montant=" + montant + ", " : "") +
                (montantCalcule != null ? "montantCalcule=" + montantCalcule + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (impotAnnuelId != null ? "impotAnnuelId=" + impotAnnuelId + ", " : "") +
                (declarationAnnuelleId != null ? "declarationAnnuelleId=" + declarationAnnuelleId + ", " : "") +
            "}";
    }

}
