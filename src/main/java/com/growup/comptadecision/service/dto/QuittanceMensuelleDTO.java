package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the QuittanceMensuelle entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleDTO {

    public QuittanceMensuelleDTO() {
    }

    private Long id;

    @NotNull
    private Integer annee;

    @NotNull
    private Integer mois;

    @NotNull
    private TypeDeclaration typeDeclaration;

    private String numeroQuittance;

    private LocalDate datePaiement;

    private BigDecimal montantTotal = BigDecimal.ZERO;

    private Long ficheClientId;

    private Long parentQuittanceId;

    private String statut;

    private String ficheClientDesignation;

    private String ficheClientMatriculeFiscale;

    private String ficheClientRegistreCommerce;

    private LocalDate ficheClientDateCreation;

    private Float valeurImpot;

    private BigDecimal montantTaxes;

    private BigDecimal montantPenalite = BigDecimal.ZERO;

    private List<QuittanceMensuelleLigneDTO> quittanceMensuelleLignes = new ArrayList<QuittanceMensuelleLigneDTO>();

    private List<BusinessAlertDTO> businessAlerts = new ArrayList<>();

    public Float getValeurImpot() {
        return valeurImpot;
    }

    public void setValeurImpot(Float valeurImpot) {
        this.valeurImpot = valeurImpot;
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

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public List<QuittanceMensuelleLigneDTO> getQuittanceMensuelleLignes() {
        return quittanceMensuelleLignes;
    }

    public void setQuittanceMensuelleLignes(List<QuittanceMensuelleLigneDTO> quittanceMensuelleLignes) {
        this.quittanceMensuelleLignes = quittanceMensuelleLignes;
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

    public Long getParentQuittanceId() {
        return parentQuittanceId;
    }

    public void setParentQuittanceId(Long parentQuittanceId) {
        this.parentQuittanceId = parentQuittanceId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<BusinessAlertDTO> getBusinessAlerts() {
        return businessAlerts;
    }

    public void setBusinessAlerts(List<BusinessAlertDTO> businessAlerts) {
        this.businessAlerts = businessAlerts;
    }

    public void addBusinessAlert(BusinessAlertDTO businessAlert) {
        if (businessAlert != null) {
            this.businessAlerts.add(businessAlert);
        }
    }

    public void addBusinessAlerts(List<BusinessAlertDTO> businessAlerts) {
        if (businessAlerts != null) {
            this.businessAlerts.addAll(businessAlerts);
        }
    }

    public BigDecimal getMontantTaxes() {
        return montantTaxes;
    }

    public void setMontantTaxes(BigDecimal montantTaxes) {
        this.montantTaxes = montantTaxes;
    }

    public BigDecimal getMontantPenalite() {
        return montantPenalite;
    }

    public void setMontantPenalite(BigDecimal montantPenalite) {
        this.montantPenalite = montantPenalite;
    }
}
