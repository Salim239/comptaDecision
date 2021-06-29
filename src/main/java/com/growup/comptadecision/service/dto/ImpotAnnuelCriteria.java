package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the ImpotAnnuel entity. This class is used in ImpotAnnuelResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /impot-annuels?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ImpotAnnuelCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter libelle;

    private StringFilter description;

    private BooleanFilter calcule;

    private IntegerFilter triOrdre;

    private LongFilter impotAnnuelLigneId;

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

    public BooleanFilter getCalcule() {
        return calcule;
    }

    public void setCalcule(BooleanFilter calcule) {
        this.calcule = calcule;
    }

    public IntegerFilter getTriOrdre() {
        return triOrdre;
    }

    public void setTriOrdre(IntegerFilter triOrdre) {
        this.triOrdre = triOrdre;
    }

    public LongFilter getImpotAnnuelLigneId() {
        return impotAnnuelLigneId;
    }

    public void setImpotAnnuelLigneId(LongFilter impotAnnuelLigneId) {
        this.impotAnnuelLigneId = impotAnnuelLigneId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImpotAnnuelCriteria that = (ImpotAnnuelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(calcule, that.calcule) &&
            Objects.equals(triOrdre, that.triOrdre) &&
            Objects.equals(impotAnnuelLigneId, that.impotAnnuelLigneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        description,
        calcule,
        triOrdre,
        impotAnnuelLigneId
        );
    }

    @Override
    public String toString() {
        return "ImpotAnnuelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (calcule != null ? "calcule=" + calcule + ", " : "") +
                (triOrdre != null ? "triOrdre=" + triOrdre + ", " : "") +
                (impotAnnuelLigneId != null ? "impotAnnuelLigneId=" + impotAnnuelLigneId + ", " : "") +
            "}";
    }

}
