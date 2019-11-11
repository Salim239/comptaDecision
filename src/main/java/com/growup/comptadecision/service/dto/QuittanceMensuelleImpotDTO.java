package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the QuittanceMensuelleImpot entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpotDTO implements Serializable {

    public QuittanceMensuelleImpotDTO() {
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

    private BigDecimal montantTotal;

    private Long ficheClientId;

    private String ficheClientDesignation;
    private String ficheClientMatriculeFiscale;
    private String ficheClientRegistreCommerce;
    private LocalDate ficheClientDateCreation;

    private Float valeurImpot;

    private List<QuittanceMensuelleImpotDetailDTO> quittanceMensuelleImpotDetails = new ArrayList<QuittanceMensuelleImpotDetailDTO>();

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

    public List<QuittanceMensuelleImpotDetailDTO> getQuittanceMensuelleImpotDetails() {
        return quittanceMensuelleImpotDetails;
    }

    public void setQuittanceMensuelleImpotDetails(List<QuittanceMensuelleImpotDetailDTO> quittanceMensuelleImpotDetails) {
        this.quittanceMensuelleImpotDetails = quittanceMensuelleImpotDetails;
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
