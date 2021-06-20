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
 * A DTO for the AcompteProvisionnel entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class AcompteProvisionnelDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer annee;

    @NotNull
    private Integer numero;

    private LocalDate date;

    private String numeroQuittance;

    private BigDecimal montantBase;
    private BigDecimal montantBaseCalc;

    private BigDecimal montantAcompteProvisionnel;

    private BigDecimal montantReportAnterieur;
    private BigDecimal montantReportAnterieurCalc;

    private BigDecimal montantRetenueSource;

    private BigDecimal montantNet;

    private TypeDeclaration type;

    private StatutDeclaration statut;

    private List<BusinessAlertDTO> businessAlerts = new ArrayList<>();


    private Long ficheClientId;
    private String ficheClientDesignation;
    private String ficheClientMatriculeFiscale;
    private String ficheClientRegistreCommerce;
    private LocalDate ficheClientDateCreation;

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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public BigDecimal getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(BigDecimal montantBase) {
        this.montantBase = montantBase;
    }

    public BigDecimal getMontantAcompteProvisionnel() {
        return montantAcompteProvisionnel;
    }

    public void setMontantAcompteProvisionnel(BigDecimal montantAcompteProvisionnel) {
        this.montantAcompteProvisionnel = montantAcompteProvisionnel;
    }

    public BigDecimal getMontantReportAnterieur() {
        return montantReportAnterieur;
    }

    public void setMontantReportAnterieur(BigDecimal montantReportAnterieur) {
        this.montantReportAnterieur = montantReportAnterieur;
    }

    public BigDecimal getMontantRetenueSource() {
        return montantRetenueSource;
    }

    public void setMontantRetenueSource(BigDecimal montantRetenueSource) {
        this.montantRetenueSource = montantRetenueSource;
    }

    public BigDecimal getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

   public LocalDate getFicheClientDateCreation() {
        return ficheClientDateCreation;
    }

    public void setFicheClientDateCreation(LocalDate ficheClientDateCreation) {
        this.ficheClientDateCreation = ficheClientDateCreation;
    }

    public TypeDeclaration getType() {
        return type;
    }

    public void setType(TypeDeclaration type) {
        this.type = type;
    }

    public StatutDeclaration getStatut() {
        return statut;
    }

    public void setStatut(StatutDeclaration statut) {
        this.statut = statut;
    }

    public List<BusinessAlertDTO> getBusinessAlerts() {
        return businessAlerts;
    }

    public void setBusinessAlerts(List<BusinessAlertDTO> businessAlerts) {
        this.businessAlerts = businessAlerts;
    }

    public void addBusinessAlertDTO(BusinessAlertDTO businessAlertDTO) {

        if (this.businessAlerts == null) {
            this.businessAlerts = new ArrayList<>();
        }
            this.businessAlerts.add(businessAlertDTO);

    }

    public BigDecimal getMontantBaseCalc() {
        return montantBaseCalc;
    }

    public void setMontantBaseCalc(BigDecimal montantBaseCalc) {
        this.montantBaseCalc = montantBaseCalc;
    }

    public BigDecimal getMontantReportAnterieurCalc() {
        return montantReportAnterieurCalc;
    }

    public void setMontantReportAnterieurCalc(BigDecimal montantReportAnterieurCalc) {
        this.montantReportAnterieurCalc = montantReportAnterieurCalc;
    }
}
