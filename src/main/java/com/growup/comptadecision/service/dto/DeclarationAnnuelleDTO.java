package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @NotNull
    private Integer annee;

    private LocalDate datePaiement;

    private String numeroQuittance;

    private BigDecimal montantChiffreAffaireHT;

    private BigDecimal montantChiffreAffaireExport;

    private BigDecimal montantChiffreAffaireLocal;

    private BigDecimal montantChiffreAffaireTTC;

    private BigDecimal montantResultatComptable;

    private BigDecimal montantResultatFiscal;

    private BigDecimal montantAutreDeduction;

    private BigDecimal montantBaseImposable;

    private BigDecimal montantImpotLiquide;

    private BigDecimal montantAcompteProvisionnel;

    private BigDecimal montantRetenueSource;

    private BigDecimal montantNetAPaye;

    private LocalDate ficheClientDateCreation;


    private Long ficheClientId;

    private String ficheClientDesignation;
    private String ficheClientMatriculeFiscale;
    private String ficheClientRegistreCommerce;

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

    public BigDecimal getMontantChiffreAffaireHT() {
        return montantChiffreAffaireHT;
    }

    public void setMontantChiffreAffaireHT(BigDecimal montantChiffreAffaireHT) {
        this.montantChiffreAffaireHT = montantChiffreAffaireHT;
    }

    public BigDecimal getMontantChiffreAffaireExport() {
        return montantChiffreAffaireExport;
    }

    public void setMontantChiffreAffaireExport(BigDecimal montantChiffreAffaireExport) {
        this.montantChiffreAffaireExport = montantChiffreAffaireExport;
    }

    public BigDecimal getMontantChiffreAffaireLocal() {
        return montantChiffreAffaireLocal;
    }

    public void setMontantChiffreAffaireLocal(BigDecimal montantChiffreAffaireLocal) {
        this.montantChiffreAffaireLocal = montantChiffreAffaireLocal;
    }

    public BigDecimal getMontantChiffreAffaireTTC() {
        return montantChiffreAffaireTTC;
    }

    public void setMontantChiffreAffaireTTC(BigDecimal montantChiffreAffaireTTC) {
        this.montantChiffreAffaireTTC = montantChiffreAffaireTTC;
    }

    public BigDecimal getMontantResultatComptable() {
        return montantResultatComptable;
    }

    public void setMontantResultatComptable(BigDecimal montantResultatComptable) {
        this.montantResultatComptable = montantResultatComptable;
    }

    public BigDecimal getMontantResultatFiscal() {
        return montantResultatFiscal;
    }

    public void setMontantResultatFiscal(BigDecimal montantResultatFiscal) {
        this.montantResultatFiscal = montantResultatFiscal;
    }

    public BigDecimal getMontantAutreDeduction() {
        return montantAutreDeduction;
    }

    public void setMontantAutreDeduction(BigDecimal montantAutreDeduction) {
        this.montantAutreDeduction = montantAutreDeduction;
    }

    public BigDecimal getMontantBaseImposable() {
        return montantBaseImposable;
    }

    public void setMontantBaseImposable(BigDecimal montantBaseImposable) {
        this.montantBaseImposable = montantBaseImposable;
    }

    public BigDecimal getMontantImpotLiquide() {
        return montantImpotLiquide;
    }

    public void setMontantImpotLiquide(BigDecimal montantImpotLiquide) {
        this.montantImpotLiquide = montantImpotLiquide;
    }

    public BigDecimal getMontantAcompteProvisionnel() {
        return montantAcompteProvisionnel;
    }

    public void setMontantAcompteProvisionnel(BigDecimal montantAcompteProvisionnel) {
        this.montantAcompteProvisionnel = montantAcompteProvisionnel;
    }

    public BigDecimal getMontantRetenueSource() {
        return montantRetenueSource;
    }

    public void setMontantRetenueSource(BigDecimal montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
    }

    public BigDecimal getMontantNetAPaye() {
        return montantNetAPaye;
    }

    public void setMontantNetAPaye(BigDecimal montantNetAPaye) {
        this.montantNetAPaye = montantNetAPaye;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public LocalDate getFicheClientDateCreation() {
        return ficheClientDateCreation;
    }

    public void setFicheClientDateCreation(LocalDate ficheClientDateCreation) {
        this.ficheClientDateCreation = ficheClientDateCreation;
    }

}
