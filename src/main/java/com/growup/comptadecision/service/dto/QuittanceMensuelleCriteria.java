package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the QuittanceMensuelle entity. This class is used in QuittanceMensuelleResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quittance-mensuelles?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuittanceMensuelleCriteria implements Serializable {
    /**
     * Class for filtering TypeDeclaration
     */
    public static class TypeDeclarationFilter extends Filter<TypeDeclaration> {
    }
    /**
     * Class for filtering StatutDeclaration
     */
    public static class StatutDeclarationFilter extends Filter<StatutDeclaration> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter annee;

    private IntegerFilter mois;

    private StringFilter numeroQuittance;

    private LocalDateFilter datePaiement;

    private BigDecimalFilter montantTotal;

    private TypeDeclarationFilter typeDeclaration;

    private StatutDeclarationFilter statut;

    private LongFilter quittanceMensuelleLigneId;

    private LongFilter ficheClientId;

    private LongFilter parentQuittanceId;

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

    public IntegerFilter getMois() {
        return mois;
    }

    public void setMois(IntegerFilter mois) {
        this.mois = mois;
    }

    public StringFilter getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(StringFilter numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public LocalDateFilter getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateFilter datePaiement) {
        this.datePaiement = datePaiement;
    }

    public BigDecimalFilter getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimalFilter montantTotal) {
        this.montantTotal = montantTotal;
    }

    public TypeDeclarationFilter getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclarationFilter typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public StatutDeclarationFilter getStatut() {
        return statut;
    }

    public void setStatut(StatutDeclarationFilter statut) {
        this.statut = statut;
    }

    public LongFilter getQuittanceMensuelleLigneId() {
        return quittanceMensuelleLigneId;
    }

    public void setQuittanceMensuelleLigneId(LongFilter quittanceMensuelleLigneId) {
        this.quittanceMensuelleLigneId = quittanceMensuelleLigneId;
    }

    public LongFilter getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(LongFilter ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public LongFilter getParentQuittanceId() {
        return parentQuittanceId;
    }

    public void setParentQuittanceId(LongFilter parentQuittanceId) {
        this.parentQuittanceId = parentQuittanceId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QuittanceMensuelleCriteria that = (QuittanceMensuelleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(annee, that.annee) &&
            Objects.equals(mois, that.mois) &&
            Objects.equals(numeroQuittance, that.numeroQuittance) &&
            Objects.equals(datePaiement, that.datePaiement) &&
            Objects.equals(montantTotal, that.montantTotal) &&
            Objects.equals(typeDeclaration, that.typeDeclaration) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(quittanceMensuelleLigneId, that.quittanceMensuelleLigneId) &&
            Objects.equals(ficheClientId, that.ficheClientId) &&
            Objects.equals(parentQuittanceId, that.parentQuittanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        annee,
        mois,
        numeroQuittance,
        datePaiement,
        montantTotal,
        typeDeclaration,
        statut,
        quittanceMensuelleLigneId,
        ficheClientId,
        parentQuittanceId
        );
    }

    @Override
    public String toString() {
        return "QuittanceMensuelleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (annee != null ? "annee=" + annee + ", " : "") +
                (mois != null ? "mois=" + mois + ", " : "") +
                (numeroQuittance != null ? "numeroQuittance=" + numeroQuittance + ", " : "") +
                (datePaiement != null ? "datePaiement=" + datePaiement + ", " : "") +
                (montantTotal != null ? "montantTotal=" + montantTotal + ", " : "") +
                (typeDeclaration != null ? "typeDeclaration=" + typeDeclaration + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (quittanceMensuelleLigneId != null ? "quittanceMensuelleLigneId=" + quittanceMensuelleLigneId + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
                (parentQuittanceId != null ? "parentQuittanceId=" + parentQuittanceId + ", " : "") +
            "}";
    }

}
