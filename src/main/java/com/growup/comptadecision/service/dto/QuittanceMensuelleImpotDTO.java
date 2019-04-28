package com.growup.comptadecision.service.dto;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the QuittanceMensuelleImpot entity.
 */
public class QuittanceMensuelleImpotDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer annee;

    @NotNull
    private Integer mois;

    @NotNull
    private TypeDeclaration typeDeclaration;

    private String numeroQuittance;

    private LocalDate datePaiement;

    private BigDecimal montantPaye;

    private List<QuittanceMensuelleImpotLineDTO> quittanceMensuelleImpotLines = new ArrayList<QuittanceMensuelleImpotLineDTO>();


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

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public List<QuittanceMensuelleImpotLineDTO> getQuittanceMensuelleImpotLines() {
        return quittanceMensuelleImpotLines;
    }

    public void setQuittanceMensuelleImpotLines(List<QuittanceMensuelleImpotLineDTO> quittanceMensuelleImpotLines) {
        this.quittanceMensuelleImpotLines = quittanceMensuelleImpotLines;
    }

    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    @Override
    public String toString() {
        return "QuittanceMensuelleImpotDTO{" +
                "id=" + id +
                ", annee=" + annee +
                ", mois=" + mois +
                ", typeDeclaration=" + typeDeclaration +
                ", numeroQuittance='" + numeroQuittance + '\'' +
                ", datePaiement=" + datePaiement +
                ", montantPaye=" + montantPaye +
                ", quittanceMensuelleImpotLines=" + quittanceMensuelleImpotLines +
                ", ficheClientId=" + ficheClientId +
                ", ficheClientDesignation='" + ficheClientDesignation + '\'' +
                ", ficheClientMatriculeFiscale='" + ficheClientMatriculeFiscale + '\'' +
                ", ficheClientRegistreCommerce='" + ficheClientRegistreCommerce + '\'' +
                ", ficheClientDateCreation=" + ficheClientDateCreation +
                '}';
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = (QuittanceMensuelleImpotDTO) o;
        if (quittanceMensuelleImpotDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quittanceMensuelleImpotDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
