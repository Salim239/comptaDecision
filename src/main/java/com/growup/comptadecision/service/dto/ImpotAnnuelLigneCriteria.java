package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the ImpotAnnuelLigne entity. This class is used in ImpotAnnuelLigneResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /impot-annuel-lignes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ImpotAnnuelLigneCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter coefficient;

    private LongFilter impotMensuelId;

    private LongFilter impotAnnuelId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(FloatFilter coefficient) {
        this.coefficient = coefficient;
    }

    public LongFilter getImpotMensuelId() {
        return impotMensuelId;
    }

    public void setImpotMensuelId(LongFilter impotMensuelId) {
        this.impotMensuelId = impotMensuelId;
    }

    public LongFilter getImpotAnnuelId() {
        return impotAnnuelId;
    }

    public void setImpotAnnuelId(LongFilter impotAnnuelId) {
        this.impotAnnuelId = impotAnnuelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImpotAnnuelLigneCriteria that = (ImpotAnnuelLigneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(coefficient, that.coefficient) &&
            Objects.equals(impotMensuelId, that.impotMensuelId) &&
            Objects.equals(impotAnnuelId, that.impotAnnuelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        coefficient,
        impotMensuelId,
        impotAnnuelId
        );
    }

    @Override
    public String toString() {
        return "ImpotAnnuelLigneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (coefficient != null ? "coefficient=" + coefficient + ", " : "") +
                (impotMensuelId != null ? "impotMensuelId=" + impotMensuelId + ", " : "") +
                (impotAnnuelId != null ? "impotAnnuelId=" + impotAnnuelId + ", " : "") +
            "}";
    }

}
