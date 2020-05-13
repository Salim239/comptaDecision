package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the DeclarationAnnuelle entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class DeclarationAnnuelleDTO implements Serializable {

    private Long id;

    @NotNull
    private TypeDeclaration typeDeclaration;

    private StatutDeclaration status;

    @NotNull
    private Integer annee;

    private LocalDate datePaiement;

    private String numeroQuittance;

    private BigDecimal montantImpotAnnuel;

    private BigDecimal montantApPayes;

    private BigDecimal montantApPayesCalc;

    private BigDecimal montantRetenueSource;

    private BigDecimal montantReportAnterieur;

    private BigDecimal montantReportAnterieurCalc;

    private BigDecimal montantNet;

    private LocalDate ficheClientDateCreation;
    private Long ficheClientId;
    private String ficheClientDesignation;
    private String ficheClientMatriculeFiscale;
    private String ficheClientRegistreCommerce;

    List<DeclarationAnnuelleDetailDTO> declarationAnnuelleDetails = new ArrayList<>();

    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimal getMontantImpotAnnuel() {
        return montantImpotAnnuel;
    }

    public void setMontantImpotAnnuel(BigDecimal montantImpotAnnuel) {
        this.montantImpotAnnuel = montantImpotAnnuel;
    }

    public BigDecimal getMontantApPayes() {
        return montantApPayes;
    }

    public void setMontantApPayes(BigDecimal montantApPayes) {
        this.montantApPayes = montantApPayes;
    }

    public BigDecimal getMontantRetenueSource() {
        return montantRetenueSource;
    }

    public void setMontantRetenueSource(BigDecimal montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
    }

    public BigDecimal getMontantReportAnterieur() {
        return montantReportAnterieur;
    }

    public void setMontantReportAnterieur(BigDecimal montantReportAnterieur) {
        this.montantReportAnterieur = montantReportAnterieur;
    }

    public BigDecimal getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
    }

    public LocalDate getFicheClientDateCreation() {
        return ficheClientDateCreation;
    }

    public void setFicheClientDateCreation(LocalDate ficheClientDateCreation) {
        this.ficheClientDateCreation = ficheClientDateCreation;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
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

    public List<DeclarationAnnuelleDetailDTO> getDeclarationAnnuelleDetails() {
        return declarationAnnuelleDetails;
    }

    public void setDeclarationAnnuelleDetails(List<DeclarationAnnuelleDetailDTO> declarationAnnuelleDetails) {
        this.declarationAnnuelleDetails = declarationAnnuelleDetails;
    }

    public BigDecimal getMontantApPayesCalc() {
        return montantApPayesCalc;
    }

    public void setMontantApPayesCalc(BigDecimal montantApPayesCalc) {
        this.montantApPayesCalc = montantApPayesCalc;
    }

    public BigDecimal getMontantReportAnterieurCalc() {
        return montantReportAnterieurCalc;
    }

    public void setMontantReportAnterieurCalc(BigDecimal montantReportAnterieurCalc) {
        this.montantReportAnterieurCalc = montantReportAnterieurCalc;
    }

    public StatutDeclaration getStatus() {
        return status;
    }

    public void setStatus(StatutDeclaration status) {
        this.status = status;
    }
}
