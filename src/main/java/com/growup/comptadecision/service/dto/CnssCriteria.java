package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeCnss;
import com.growup.comptadecision.domain.enumeration.TypeDeclarationCnss;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the Cnss entity. This class is used in CnssResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /cnsses?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CnssCriteria implements Serializable {
    /**
     * Class for filtering TypeCnss
     */
    public static class TypeCnssFilter extends Filter<TypeCnss> {
    }
    /**
     * Class for filtering TypeDeclarationCnss
     */
    public static class TypeDeclarationCnssFilter extends Filter<TypeDeclarationCnss> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter annee;

    private IntegerFilter trimestre;

    private LocalDateFilter date;

    private StringFilter numeroQuittance;

    private BigDecimalFilter montantSalaireBrutNormal;

    private BigDecimalFilter montantSalaireBrutKarama;

    private TypeCnssFilter typeCnss;

    private TypeDeclarationCnssFilter typeDeclaration;

    private BigDecimalFilter tauxCnssNormal;

    private BigDecimalFilter tauxCnssKarama;

    private BigDecimalFilter tauxCnssNormalAccident;

    private BigDecimalFilter tauxCnssKaramaAccident;

    private BigDecimalFilter montantCnssNormal;

    private BigDecimalFilter montantCnssKarama;

    private BigDecimalFilter montantTotalCnss;

    private BigDecimalFilter montantTotalSalaireBrut;

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

    public IntegerFilter getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(IntegerFilter trimestre) {
        this.trimestre = trimestre;
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

    public BigDecimalFilter getMontantSalaireBrutNormal() {
        return montantSalaireBrutNormal;
    }

    public void setMontantSalaireBrutNormal(BigDecimalFilter montantSalaireBrutNormal) {
        this.montantSalaireBrutNormal = montantSalaireBrutNormal;
    }

    public BigDecimalFilter getMontantSalaireBrutKarama() {
        return montantSalaireBrutKarama;
    }

    public void setMontantSalaireBrutKarama(BigDecimalFilter montantSalaireBrutKarama) {
        this.montantSalaireBrutKarama = montantSalaireBrutKarama;
    }

    public TypeCnssFilter getTypeCnss() {
        return typeCnss;
    }

    public void setTypeCnss(TypeCnssFilter typeCnss) {
        this.typeCnss = typeCnss;
    }

    public TypeDeclarationCnssFilter getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclarationCnssFilter typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public BigDecimalFilter getTauxCnssNormal() {
        return tauxCnssNormal;
    }

    public void setTauxCnssNormal(BigDecimalFilter tauxCnssNormal) {
        this.tauxCnssNormal = tauxCnssNormal;
    }

    public BigDecimalFilter getTauxCnssKarama() {
        return tauxCnssKarama;
    }

    public void setTauxCnssKarama(BigDecimalFilter tauxCnssKarama) {
        this.tauxCnssKarama = tauxCnssKarama;
    }

    public BigDecimalFilter getTauxCnssNormalAccident() {
        return tauxCnssNormalAccident;
    }

    public void setTauxCnssNormalAccident(BigDecimalFilter tauxCnssNormalAccident) {
        this.tauxCnssNormalAccident = tauxCnssNormalAccident;
    }

    public BigDecimalFilter getTauxCnssKaramaAccident() {
        return tauxCnssKaramaAccident;
    }

    public void setTauxCnssKaramaAccident(BigDecimalFilter tauxCnssKaramaAccident) {
        this.tauxCnssKaramaAccident = tauxCnssKaramaAccident;
    }

    public BigDecimalFilter getMontantCnssNormal() {
        return montantCnssNormal;
    }

    public void setMontantCnssNormal(BigDecimalFilter montantCnssNormal) {
        this.montantCnssNormal = montantCnssNormal;
    }

    public BigDecimalFilter getMontantCnssKarama() {
        return montantCnssKarama;
    }

    public void setMontantCnssKarama(BigDecimalFilter montantCnssKarama) {
        this.montantCnssKarama = montantCnssKarama;
    }

    public BigDecimalFilter getMontantTotalCnss() {
        return montantTotalCnss;
    }

    public void setMontantTotalCnss(BigDecimalFilter montantTotalCnss) {
        this.montantTotalCnss = montantTotalCnss;
    }

    public BigDecimalFilter getMontantTotalSalaireBrut() {
        return montantTotalSalaireBrut;
    }

    public void setMontantTotalSalaireBrut(BigDecimalFilter montantTotalSalaireBrut) {
        this.montantTotalSalaireBrut = montantTotalSalaireBrut;
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
        final CnssCriteria that = (CnssCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(annee, that.annee) &&
            Objects.equals(trimestre, that.trimestre) &&
            Objects.equals(date, that.date) &&
            Objects.equals(numeroQuittance, that.numeroQuittance) &&
            Objects.equals(montantSalaireBrutNormal, that.montantSalaireBrutNormal) &&
            Objects.equals(montantSalaireBrutKarama, that.montantSalaireBrutKarama) &&
            Objects.equals(typeCnss, that.typeCnss) &&
            Objects.equals(typeDeclaration, that.typeDeclaration) &&
            Objects.equals(tauxCnssNormal, that.tauxCnssNormal) &&
            Objects.equals(tauxCnssKarama, that.tauxCnssKarama) &&
            Objects.equals(tauxCnssNormalAccident, that.tauxCnssNormalAccident) &&
            Objects.equals(tauxCnssKaramaAccident, that.tauxCnssKaramaAccident) &&
            Objects.equals(montantCnssNormal, that.montantCnssNormal) &&
            Objects.equals(montantCnssKarama, that.montantCnssKarama) &&
            Objects.equals(montantTotalCnss, that.montantTotalCnss) &&
            Objects.equals(montantTotalSalaireBrut, that.montantTotalSalaireBrut) &&
            Objects.equals(ficheClientId, that.ficheClientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        annee,
        trimestre,
        date,
        numeroQuittance,
        montantSalaireBrutNormal,
        montantSalaireBrutKarama,
        typeCnss,
        typeDeclaration,
        tauxCnssNormal,
        tauxCnssKarama,
        tauxCnssNormalAccident,
        tauxCnssKaramaAccident,
        montantCnssNormal,
        montantCnssKarama,
        montantTotalCnss,
        montantTotalSalaireBrut,
        ficheClientId
        );
    }

    @Override
    public String toString() {
        return "CnssCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (annee != null ? "annee=" + annee + ", " : "") +
                (trimestre != null ? "trimestre=" + trimestre + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (numeroQuittance != null ? "numeroQuittance=" + numeroQuittance + ", " : "") +
                (montantSalaireBrutNormal != null ? "montantSalaireBrutNormal=" + montantSalaireBrutNormal + ", " : "") +
                (montantSalaireBrutKarama != null ? "montantSalaireBrutKarama=" + montantSalaireBrutKarama + ", " : "") +
                (typeCnss != null ? "typeCnss=" + typeCnss + ", " : "") +
                (typeDeclaration != null ? "typeDeclaration=" + typeDeclaration + ", " : "") +
                (tauxCnssNormal != null ? "tauxCnssNormal=" + tauxCnssNormal + ", " : "") +
                (tauxCnssKarama != null ? "tauxCnssKarama=" + tauxCnssKarama + ", " : "") +
                (tauxCnssNormalAccident != null ? "tauxCnssNormalAccident=" + tauxCnssNormalAccident + ", " : "") +
                (tauxCnssKaramaAccident != null ? "tauxCnssKaramaAccident=" + tauxCnssKaramaAccident + ", " : "") +
                (montantCnssNormal != null ? "montantCnssNormal=" + montantCnssNormal + ", " : "") +
                (montantCnssKarama != null ? "montantCnssKarama=" + montantCnssKarama + ", " : "") +
                (montantTotalCnss != null ? "montantTotalCnss=" + montantTotalCnss + ", " : "") +
                (montantTotalSalaireBrut != null ? "montantTotalSalaireBrut=" + montantTotalSalaireBrut + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
            "}";
    }

}
