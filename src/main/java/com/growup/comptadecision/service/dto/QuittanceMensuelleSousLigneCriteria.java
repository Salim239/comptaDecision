package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the QuittanceMensuelleSousLigne entity. This class is used in QuittanceMensuelleSousLigneResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quittance-mensuelle-sous-lignes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuittanceMensuelleSousLigneCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BigDecimalFilter montantBase;

    private BigDecimalFilter montantTotal;

    private LongFilter impotMensuelLigneId;

    private LongFilter quittanceMensuelleLigneId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public BigDecimalFilter getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(BigDecimalFilter montantBase) {
        this.montantBase = montantBase;
    }

    public BigDecimalFilter getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimalFilter montantTotal) {
        this.montantTotal = montantTotal;
    }

    public LongFilter getImpotMensuelLigneId() {
        return impotMensuelLigneId;
    }

    public void setImpotMensuelLigneId(LongFilter impotMensuelLigneId) {
        this.impotMensuelLigneId = impotMensuelLigneId;
    }

    public LongFilter getQuittanceMensuelleLigneId() {
        return quittanceMensuelleLigneId;
    }

    public void setQuittanceMensuelleLigneId(LongFilter quittanceMensuelleLigneId) {
        this.quittanceMensuelleLigneId = quittanceMensuelleLigneId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QuittanceMensuelleSousLigneCriteria that = (QuittanceMensuelleSousLigneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(montantBase, that.montantBase) &&
            Objects.equals(montantTotal, that.montantTotal) &&
            Objects.equals(impotMensuelLigneId, that.impotMensuelLigneId) &&
            Objects.equals(quittanceMensuelleLigneId, that.quittanceMensuelleLigneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        montantBase,
        montantTotal,
        impotMensuelLigneId,
        quittanceMensuelleLigneId
        );
    }

    @Override
    public String toString() {
        return "QuittanceMensuelleSousLigneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (montantBase != null ? "montantBase=" + montantBase + ", " : "") +
                (montantTotal != null ? "montantTotal=" + montantTotal + ", " : "") +
                (impotMensuelLigneId != null ? "impotMensuelLigneId=" + impotMensuelLigneId + ", " : "") +
                (quittanceMensuelleLigneId != null ? "quittanceMensuelleLigneId=" + quittanceMensuelleLigneId + ", " : "") +
            "}";
    }

}
