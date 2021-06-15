package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeCnss;
import com.growup.comptadecision.domain.enumeration.TypeDeclarationCnss;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A DTO for the Cnss entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class CnssDTO  extends BusinessAlertDTO {

    private Long id;

    @NotNull
    private TypeCnss typeCnss;

    @NotNull
    private Integer annee;

    @NotNull
    private Integer trimestre;

    private LocalDate date;

    private String numeroQuittance;
    private Long ficheClientId;
    private String ficheClientDesignation;
    private String ficheClientMatriculeFiscale;
    private String ficheClientRegistreCommerce;
    private String ficheClientCnssEmployeur;
    private String ficheClientCnssGerant;
    private LocalDate ficheClientDateCreation;
    private TypeDeclarationCnss typeDeclaration;
    private BigDecimal montantSalaireBrutNormal;
    private BigDecimal montantSalaireBrutKarama;
    private BigDecimal tauxCnssNormal;
    private BigDecimal tauxCnssKarama;
    private BigDecimal tauxCnssNormalAccident;
    private BigDecimal tauxCnssKaramaAccident;
    private BigDecimal totalTauxCnssNormal;
    private BigDecimal totalTauxCnssKarama;
    private BigDecimal montantPenalite = BigDecimal.ZERO;
    private BigDecimal montantCnssNormal;
    private BigDecimal montantCnssKarama;
    private BigDecimal montantTotalCnss;
    private BigDecimal montantTotalSalaireBrut;

    public BigDecimal getTotalTauxCnssNormal() {
        return this.tauxCnssNormal != null ? this.tauxCnssNormal.add(tauxCnssNormalAccident) : null;
    }

    public void setTotalTauxCnssNormal(BigDecimal totalTauxCnssNormal) {
        this.totalTauxCnssNormal = totalTauxCnssNormal;
    }

    public BigDecimal getTotalTauxCnssKarama() {
        return this.tauxCnssKarama != null ? this.tauxCnssKarama.add(tauxCnssKaramaAccident) : null;
    }

    public void setTotalTauxCnssKarama(BigDecimal totalTauxCnssKarama) {
        this.totalTauxCnssKarama = totalTauxCnssKarama;
    }

    public BigDecimal getMontantTotalSalaireBrut() {
        return montantTotalSalaireBrut;
    }

    public void setMontantTotalSalaireBrut(BigDecimal montantTotalSalaireBrut) {
        this.montantTotalSalaireBrut = montantTotalSalaireBrut;
    }

    public BigDecimal getMontantTotalCnss() {
        return montantTotalCnss;
    }

    public void setMontantTotalCnss(BigDecimal montantTotalCnss) {
        this.montantTotalCnss = montantTotalCnss;
    }

    public String getFicheClientDesignation() {
        return ficheClientDesignation;
    }

    public void setFicheClientDesignation(String ficheClientDesignation) {
        this.ficheClientDesignation = ficheClientDesignation;
    }

    public String getFicheClientMatriculeFiscale() {
        return ficheClientMatriculeFiscale;
    }

    public void setFicheClientMatriculeFiscale(String ficheClientMatriculeFiscale) {
        this.ficheClientMatriculeFiscale = ficheClientMatriculeFiscale;
    }

    public String getFicheClientRegistreCommerce() {
        return ficheClientRegistreCommerce;
    }

    public void setFicheClientRegistreCommerce(String ficheClientRegistreCommerce) {
        this.ficheClientRegistreCommerce = ficheClientRegistreCommerce;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimal getMontantSalaireBrutNormal() {
        return montantSalaireBrutNormal;
    }

    public void setMontantSalaireBrutNormal(BigDecimal montantSalaireBrutNormal) {
        this.montantSalaireBrutNormal = montantSalaireBrutNormal;
    }

    public BigDecimal getMontantSalaireBrutKarama() {
        return montantSalaireBrutKarama;
    }

    public void setMontantSalaireBrutKarama(BigDecimal montantSalaireBrutKarama) {
        this.montantSalaireBrutKarama = montantSalaireBrutKarama;
    }

    public String getFicheClientCnssEmployeur() {
        return ficheClientCnssEmployeur;
    }

    public void setFicheClientCnssEmployeur(String ficheClientCnssEmployeur) {
        this.ficheClientCnssEmployeur = ficheClientCnssEmployeur;
    }

    public String getFicheClientCnssGerant() {
        return ficheClientCnssGerant;
    }

    public void setFicheClientCnssGerant(String ficheClientCnssGerant) {
        this.ficheClientCnssGerant = ficheClientCnssGerant;
    }

    public BigDecimal getTauxCnssKaramaAccident() {
        return tauxCnssKaramaAccident;
    }

    public void setTauxCnssKaramaAccident(BigDecimal tauxCnssKaramaAccident) {
        this.tauxCnssKaramaAccident = tauxCnssKaramaAccident;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public LocalDate getFicheClientDateCreation() {
        return ficheClientDateCreation;
    }

    public void setFicheClientDateCreation(LocalDate ficheClientDateCreation) {
        this.ficheClientDateCreation = ficheClientDateCreation;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public TypeCnss getTypeCnss() {
        return typeCnss;
    }

    public void setTypeCnss(TypeCnss typeCnss) {
        this.typeCnss = typeCnss;
    }

    public TypeDeclarationCnss getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclarationCnss typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public BigDecimal getTauxCnssNormal() {
        return tauxCnssNormal;
    }

    public void setTauxCnssNormal(BigDecimal tauxCnssNormal) {
        this.tauxCnssNormal = tauxCnssNormal;
    }

    public BigDecimal getTauxCnssKarama() {
        return tauxCnssKarama;
    }

    public void setTauxCnssKarama(BigDecimal tauxCnssKarama) {
        this.tauxCnssKarama = tauxCnssKarama;
    }

    public BigDecimal getTauxCnssNormalAccident() {
        return tauxCnssNormalAccident;
    }

    public void setTauxCnssNormalAccident(BigDecimal tauxCnssNormalAccident) {
        this.tauxCnssNormalAccident = tauxCnssNormalAccident;
    }

    public BigDecimal getMontantCnssNormal() {
        return montantCnssNormal;
    }

    public void setMontantCnssNormal(BigDecimal montantCnssNormal) {
        this.montantCnssNormal = montantCnssNormal;
    }

    public BigDecimal getMontantCnssKarama() {
        return montantCnssKarama;
    }

    public void setMontantCnssKarama(BigDecimal montantCnssKarama) {
        this.montantCnssKarama = montantCnssKarama;
    }


}
