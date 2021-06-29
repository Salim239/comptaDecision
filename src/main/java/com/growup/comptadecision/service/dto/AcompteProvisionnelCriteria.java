package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the AcompteProvisionnel entity. This class is used in AcompteProvisionnelResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /acompte-provisionnels?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AcompteProvisionnelCriteria implements Serializable {
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

    private IntegerFilter numero;

    private LocalDateFilter date;

    private StringFilter numeroQuittance;

    private BigDecimalFilter montantBase;

    private BigDecimalFilter montantAcompteProvisionnel;

    private BigDecimalFilter montantReportAnterieur;

    private BigDecimalFilter montantRetenueSource;

    private BigDecimalFilter montantNet;

    private TypeDeclarationFilter typeDeclaration;

    private StatutDeclarationFilter statut;

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

    public IntegerFilter getNumero() {
        return numero;
    }

    public void setNumero(IntegerFilter numero) {
        this.numero = numero;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public StringFilter getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(StringFilter numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimalFilter getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(BigDecimalFilter montantBase) {
        this.montantBase = montantBase;
    }

    public BigDecimalFilter getMontantAcompteProvisionnel() {
        return montantAcompteProvisionnel;
    }

    public void setMontantAcompteProvisionnel(BigDecimalFilter montantAcompteProvisionnel) {
        this.montantAcompteProvisionnel = montantAcompteProvisionnel;
    }

    public BigDecimalFilter getMontantReportAnterieur() {
        return montantReportAnterieur;
    }

    public void setMontantReportAnterieur(BigDecimalFilter montantReportAnterieur) {
        this.montantReportAnterieur = montantReportAnterieur;
    }

    public BigDecimalFilter getMontantRetenueSource() {
        return montantRetenueSource;
    }

    public void setMontantRetenueSource(BigDecimalFilter montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
    }

    public BigDecimalFilter getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimalFilter montantNet) {
        this.montantNet = montantNet;
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
        final AcompteProvisionnelCriteria that = (AcompteProvisionnelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(annee, that.annee) &&
            Objects.equals(numero, that.numero) &&
            Objects.equals(date, that.date) &&
            Objects.equals(numeroQuittance, that.numeroQuittance) &&
            Objects.equals(montantBase, that.montantBase) &&
            Objects.equals(montantAcompteProvisionnel, that.montantAcompteProvisionnel) &&
            Objects.equals(montantReportAnterieur, that.montantReportAnterieur) &&
            Objects.equals(montantRetenueSource, that.montantRetenueSource) &&
            Objects.equals(montantNet, that.montantNet) &&
            Objects.equals(typeDeclaration, that.typeDeclaration) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(ficheClientId, that.ficheClientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        annee,
        numero,
        date,
        numeroQuittance,
        montantBase,
        montantAcompteProvisionnel,
        montantReportAnterieur,
        montantRetenueSource,
        montantNet,
        typeDeclaration,
        statut,
        ficheClientId
        );
    }

    @Override
    public String toString() {
        return "AcompteProvisionnelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (annee != null ? "annee=" + annee + ", " : "") +
                (numero != null ? "numero=" + numero + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (numeroQuittance != null ? "numeroQuittance=" + numeroQuittance + ", " : "") +
                (montantBase != null ? "montantBase=" + montantBase + ", " : "") +
                (montantAcompteProvisionnel != null ? "montantAcompteProvisionnel=" + montantAcompteProvisionnel + ", " : "") +
                (montantReportAnterieur != null ? "montantReportAnterieur=" + montantReportAnterieur + ", " : "") +
                (montantRetenueSource != null ? "montantRetenueSource=" + montantRetenueSource + ", " : "") +
                (montantNet != null ? "montantNet=" + montantNet + ", " : "") +
                (typeDeclaration != null ? "typeDeclaration=" + typeDeclaration + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
            "}";
    }

}
