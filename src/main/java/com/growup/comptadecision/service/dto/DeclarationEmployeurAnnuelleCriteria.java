package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the DeclarationEmployeurAnnuelle entity. This class is used in DeclarationEmployeurAnnuelleResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /declaration-employeur-annuelles?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DeclarationEmployeurAnnuelleCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter annee;

    private BigDecimalFilter montantAnnexe1;

    private BigDecimalFilter montantAnnexe2;

    private BigDecimalFilter montantAnnexe3;

    private BigDecimalFilter montantAnnexe4;

    private BigDecimalFilter montantAnnexe5;

    private BigDecimalFilter montantAnnexe6;

    private BigDecimalFilter montantAnnexe7;

    private BigDecimalFilter montantAnnexe8;

    private BigDecimalFilter montantAnnexe9;

    private BigDecimalFilter montantAnnexe10;

    private BigDecimalFilter montantAnnexe11;

    private BigDecimalFilter montantAnnexe12;

    private LongFilter ficheClientId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getAnnee() {
        return annee;
    }

    public void setAnnee(IntegerFilter annee) {
        this.annee = annee;
    }

    public BigDecimalFilter getMontantAnnexe1() {
        return montantAnnexe1;
    }

    public void setMontantAnnexe1(BigDecimalFilter montantAnnexe1) {
        this.montantAnnexe1 = montantAnnexe1;
    }

    public BigDecimalFilter getMontantAnnexe2() {
        return montantAnnexe2;
    }

    public void setMontantAnnexe2(BigDecimalFilter montantAnnexe2) {
        this.montantAnnexe2 = montantAnnexe2;
    }

    public BigDecimalFilter getMontantAnnexe3() {
        return montantAnnexe3;
    }

    public void setMontantAnnexe3(BigDecimalFilter montantAnnexe3) {
        this.montantAnnexe3 = montantAnnexe3;
    }

    public BigDecimalFilter getMontantAnnexe4() {
        return montantAnnexe4;
    }

    public void setMontantAnnexe4(BigDecimalFilter montantAnnexe4) {
        this.montantAnnexe4 = montantAnnexe4;
    }

    public BigDecimalFilter getMontantAnnexe5() {
        return montantAnnexe5;
    }

    public void setMontantAnnexe5(BigDecimalFilter montantAnnexe5) {
        this.montantAnnexe5 = montantAnnexe5;
    }

    public BigDecimalFilter getMontantAnnexe6() {
        return montantAnnexe6;
    }

    public void setMontantAnnexe6(BigDecimalFilter montantAnnexe6) {
        this.montantAnnexe6 = montantAnnexe6;
    }

    public BigDecimalFilter getMontantAnnexe7() {
        return montantAnnexe7;
    }

    public void setMontantAnnexe7(BigDecimalFilter montantAnnexe7) {
        this.montantAnnexe7 = montantAnnexe7;
    }

    public BigDecimalFilter getMontantAnnexe8() {
        return montantAnnexe8;
    }

    public void setMontantAnnexe8(BigDecimalFilter montantAnnexe8) {
        this.montantAnnexe8 = montantAnnexe8;
    }

    public BigDecimalFilter getMontantAnnexe9() {
        return montantAnnexe9;
    }

    public void setMontantAnnexe9(BigDecimalFilter montantAnnexe9) {
        this.montantAnnexe9 = montantAnnexe9;
    }

    public BigDecimalFilter getMontantAnnexe10() {
        return montantAnnexe10;
    }

    public void setMontantAnnexe10(BigDecimalFilter montantAnnexe10) {
        this.montantAnnexe10 = montantAnnexe10;
    }

    public BigDecimalFilter getMontantAnnexe11() {
        return montantAnnexe11;
    }

    public void setMontantAnnexe11(BigDecimalFilter montantAnnexe11) {
        this.montantAnnexe11 = montantAnnexe11;
    }

    public BigDecimalFilter getMontantAnnexe12() {
        return montantAnnexe12;
    }

    public void setMontantAnnexe12(BigDecimalFilter montantAnnexe12) {
        this.montantAnnexe12 = montantAnnexe12;
    }

    public LongFilter getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(LongFilter ficheClientId) {
        this.ficheClientId = ficheClientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DeclarationEmployeurAnnuelleCriteria that = (DeclarationEmployeurAnnuelleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(annee, that.annee) &&
            Objects.equals(montantAnnexe1, that.montantAnnexe1) &&
            Objects.equals(montantAnnexe2, that.montantAnnexe2) &&
            Objects.equals(montantAnnexe3, that.montantAnnexe3) &&
            Objects.equals(montantAnnexe4, that.montantAnnexe4) &&
            Objects.equals(montantAnnexe5, that.montantAnnexe5) &&
            Objects.equals(montantAnnexe6, that.montantAnnexe6) &&
            Objects.equals(montantAnnexe7, that.montantAnnexe7) &&
            Objects.equals(montantAnnexe8, that.montantAnnexe8) &&
            Objects.equals(montantAnnexe9, that.montantAnnexe9) &&
            Objects.equals(montantAnnexe10, that.montantAnnexe10) &&
            Objects.equals(montantAnnexe11, that.montantAnnexe11) &&
            Objects.equals(montantAnnexe12, that.montantAnnexe12) &&
            Objects.equals(ficheClientId, that.ficheClientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        annee,
        montantAnnexe1,
        montantAnnexe2,
        montantAnnexe3,
        montantAnnexe4,
        montantAnnexe5,
        montantAnnexe6,
        montantAnnexe7,
        montantAnnexe8,
        montantAnnexe9,
        montantAnnexe10,
        montantAnnexe11,
        montantAnnexe12,
        ficheClientId
        );
    }

    @Override
    public String toString() {
        return "DeclarationEmployeurAnnuelleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (annee != null ? "annee=" + annee + ", " : "") +
                (montantAnnexe1 != null ? "montantAnnexe1=" + montantAnnexe1 + ", " : "") +
                (montantAnnexe2 != null ? "montantAnnexe2=" + montantAnnexe2 + ", " : "") +
                (montantAnnexe3 != null ? "montantAnnexe3=" + montantAnnexe3 + ", " : "") +
                (montantAnnexe4 != null ? "montantAnnexe4=" + montantAnnexe4 + ", " : "") +
                (montantAnnexe5 != null ? "montantAnnexe5=" + montantAnnexe5 + ", " : "") +
                (montantAnnexe6 != null ? "montantAnnexe6=" + montantAnnexe6 + ", " : "") +
                (montantAnnexe7 != null ? "montantAnnexe7=" + montantAnnexe7 + ", " : "") +
                (montantAnnexe8 != null ? "montantAnnexe8=" + montantAnnexe8 + ", " : "") +
                (montantAnnexe9 != null ? "montantAnnexe9=" + montantAnnexe9 + ", " : "") +
                (montantAnnexe10 != null ? "montantAnnexe10=" + montantAnnexe10 + ", " : "") +
                (montantAnnexe11 != null ? "montantAnnexe11=" + montantAnnexe11 + ", " : "") +
                (montantAnnexe12 != null ? "montantAnnexe12=" + montantAnnexe12 + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
            "}";
    }

}
