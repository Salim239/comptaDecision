package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the DeclarationAnnuelle entity. This class is used in DeclarationAnnuelleResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /declaration-annuelles?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DeclarationAnnuelleCriteria implements Serializable {
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

    private TypeDeclarationFilter typeDeclaration;

    private IntegerFilter annee;

    private LocalDateFilter datePaiement;

    private StringFilter numeroQuittance;

    private BigDecimalFilter montantImpotAnnuel;

    private BigDecimalFilter montantApPayes;

    private BigDecimalFilter montantRetenueSource;

    private BigDecimalFilter montantReportAnterieur;

    private BigDecimalFilter montantNet;

    private StatutDeclarationFilter statut;

    private LongFilter declarationAnnuelleLigneId;

    private LongFilter ficheClientId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public TypeDeclarationFilter getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclarationFilter typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public IntegerFilter getAnnee() {
        return annee;
    }

    public void setAnnee(IntegerFilter annee) {
        this.annee = annee;
    }

    public LocalDateFilter getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateFilter datePaiement) {
        this.datePaiement = datePaiement;
    }

    public StringFilter getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(StringFilter numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimalFilter getMontantImpotAnnuel() {
        return montantImpotAnnuel;
    }

    public void setMontantImpotAnnuel(BigDecimalFilter montantImpotAnnuel) {
        this.montantImpotAnnuel = montantImpotAnnuel;
    }

    public BigDecimalFilter getMontantApPayes() {
        return montantApPayes;
    }

    public void setMontantApPayes(BigDecimalFilter montantApPayes) {
        this.montantApPayes = montantApPayes;
    }

    public BigDecimalFilter getMontantRetenueSource() {
        return montantRetenueSource;
    }

    public void setMontantRetenueSource(BigDecimalFilter montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
    }

    public BigDecimalFilter getMontantReportAnterieur() {
        return montantReportAnterieur;
    }

    public void setMontantReportAnterieur(BigDecimalFilter montantReportAnterieur) {
        this.montantReportAnterieur = montantReportAnterieur;
    }

    public BigDecimalFilter getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimalFilter montantNet) {
        this.montantNet = montantNet;
    }

    public StatutDeclarationFilter getStatut() {
        return statut;
    }

    public void setStatut(StatutDeclarationFilter statut) {
        this.statut = statut;
    }

    public LongFilter getDeclarationAnnuelleLigneId() {
        return declarationAnnuelleLigneId;
    }

    public void setDeclarationAnnuelleLigneId(LongFilter declarationAnnuelleLigneId) {
        this.declarationAnnuelleLigneId = declarationAnnuelleLigneId;
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
        final DeclarationAnnuelleCriteria that = (DeclarationAnnuelleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(typeDeclaration, that.typeDeclaration) &&
            Objects.equals(annee, that.annee) &&
            Objects.equals(datePaiement, that.datePaiement) &&
            Objects.equals(numeroQuittance, that.numeroQuittance) &&
            Objects.equals(montantImpotAnnuel, that.montantImpotAnnuel) &&
            Objects.equals(montantApPayes, that.montantApPayes) &&
            Objects.equals(montantRetenueSource, that.montantRetenueSource) &&
            Objects.equals(montantReportAnterieur, that.montantReportAnterieur) &&
            Objects.equals(montantNet, that.montantNet) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(declarationAnnuelleLigneId, that.declarationAnnuelleLigneId) &&
            Objects.equals(ficheClientId, that.ficheClientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        typeDeclaration,
        annee,
        datePaiement,
        numeroQuittance,
        montantImpotAnnuel,
        montantApPayes,
        montantRetenueSource,
        montantReportAnterieur,
        montantNet,
        statut,
        declarationAnnuelleLigneId,
        ficheClientId
        );
    }

    @Override
    public String toString() {
        return "DeclarationAnnuelleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (typeDeclaration != null ? "typeDeclaration=" + typeDeclaration + ", " : "") +
                (annee != null ? "annee=" + annee + ", " : "") +
                (datePaiement != null ? "datePaiement=" + datePaiement + ", " : "") +
                (numeroQuittance != null ? "numeroQuittance=" + numeroQuittance + ", " : "") +
                (montantImpotAnnuel != null ? "montantImpotAnnuel=" + montantImpotAnnuel + ", " : "") +
                (montantApPayes != null ? "montantApPayes=" + montantApPayes + ", " : "") +
                (montantRetenueSource != null ? "montantRetenueSource=" + montantRetenueSource + ", " : "") +
                (montantReportAnterieur != null ? "montantReportAnterieur=" + montantReportAnterieur + ", " : "") +
                (montantNet != null ? "montantNet=" + montantNet + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (declarationAnnuelleLigneId != null ? "declarationAnnuelleLigneId=" + declarationAnnuelleLigneId + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
            "}";
    }

}
