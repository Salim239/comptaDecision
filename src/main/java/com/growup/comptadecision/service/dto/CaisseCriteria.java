package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the Caisse entity. This class is used in CaisseResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /caisses?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaisseCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BigDecimalFilter montantTotal;

    private BigDecimalFilter montantReport;

    private BooleanFilter cloturee;

    private LongFilter ligneCaisseId;

    private LongFilter ficheClientId;

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

    public BigDecimalFilter getMontantReport() {
        return montantReport;
    }

    public void setMontantReport(BigDecimalFilter montantReport) {
        this.montantReport = montantReport;
    }

    public BooleanFilter getCloturee() {
        return cloturee;
    }

    public void setCloturee(BooleanFilter cloturee) {
        this.cloturee = cloturee;
    }

    public LongFilter getLigneCaisseId() {
        return ligneCaisseId;
    }

    public void setLigneCaisseId(LongFilter ligneCaisseId) {
        this.ligneCaisseId = ligneCaisseId;
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
        final CaisseCriteria that = (CaisseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(montantTotal, that.montantTotal) &&
            Objects.equals(montantReport, that.montantReport) &&
            Objects.equals(cloturee, that.cloturee) &&
            Objects.equals(ligneCaisseId, that.ligneCaisseId) &&
            Objects.equals(ficheClientId, that.ficheClientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        montantTotal,
        montantReport,
        cloturee,
        ligneCaisseId,
        ficheClientId
        );
    }

    @Override
    public String toString() {
        return "CaisseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (montantTotal != null ? "montantTotal=" + montantTotal + ", " : "") +
                (montantReport != null ? "montantReport=" + montantReport + ", " : "") +
                (cloturee != null ? "cloturee=" + cloturee + ", " : "") +
                (ligneCaisseId != null ? "ligneCaisseId=" + ligneCaisseId + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
            "}";
    }

}
