package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the ImpotMensuelClient entity. This class is used in ImpotMensuelClientResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /impot-mensuel-clients?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ImpotMensuelClientCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter mois;

    private BooleanFilter applicable;

    private LongFilter impotMensuelId;

    private LongFilter ficheClientId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getMois() {
        return mois;
    }

    public void setMois(IntegerFilter mois) {
        this.mois = mois;
    }

    public BooleanFilter getApplicable() {
        return applicable;
    }

    public void setApplicable(BooleanFilter applicable) {
        this.applicable = applicable;
    }

    public LongFilter getImpotMensuelId() {
        return impotMensuelId;
    }

    public void setImpotMensuelId(LongFilter impotMensuelId) {
        this.impotMensuelId = impotMensuelId;
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
        final ImpotMensuelClientCriteria that = (ImpotMensuelClientCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mois, that.mois) &&
            Objects.equals(applicable, that.applicable) &&
            Objects.equals(impotMensuelId, that.impotMensuelId) &&
            Objects.equals(ficheClientId, that.ficheClientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mois,
        applicable,
        impotMensuelId,
        ficheClientId
        );
    }

    @Override
    public String toString() {
        return "ImpotMensuelClientCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mois != null ? "mois=" + mois + ", " : "") +
                (applicable != null ? "applicable=" + applicable + ", " : "") +
                (impotMensuelId != null ? "impotMensuelId=" + impotMensuelId + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
            "}";
    }

}
