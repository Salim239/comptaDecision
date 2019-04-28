package com.growup.comptadecision.service.dto;
import com.growup.comptadecision.domain.enumeration.TypeCnss;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Cnss entity.
 */
public class CnssDTO implements Serializable {

    private Long id;

    @NotNull
    private TypeCnss typeCnss;

    @NotNull
    private Integer annee;

    @NotNull
    private Integer trimestre;

    private LocalDate date;

    private String numeroQuittance;

    private BigDecimal montantSalaireBrutNormal;

    private BigDecimal montantSalaireBrutKarama;

    private BigDecimal montantSalaireBrutAutre;

    private BigDecimal montantChiffreAffaireTTC;

    private BigDecimal tot;

    private BigDecimal cnss;


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

    public BigDecimal getMontantSalaireBrutAutre() {
        return montantSalaireBrutAutre;
    }

    public void setMontantSalaireBrutAutre(BigDecimal montantSalaireBrutAutre) {
        this.montantSalaireBrutAutre = montantSalaireBrutAutre;
    }

    public BigDecimal getMontantChiffreAffaireTTC() {
        return montantChiffreAffaireTTC;
    }

    public void setMontantChiffreAffaireTTC(BigDecimal montantChiffreAffaireTTC) {
        this.montantChiffreAffaireTTC = montantChiffreAffaireTTC;
    }

    public BigDecimal getTot() {
        return tot;
    }

    public void setTot(BigDecimal tot) {
        this.tot = tot;
    }

    public BigDecimal getCnss() {
        return cnss;
    }

    @Override
    public String toString() {
        return "CnssDTO{" +
                "id=" + id +
                ", typeCnss=" + typeCnss +
                ", annee=" + annee +
                ", trimestre=" + trimestre +
                ", date=" + date +
                ", numeroQuittance='" + numeroQuittance + '\'' +
                ", montantSalaireBrutNormal=" + montantSalaireBrutNormal +
                ", montantSalaireBrutKarama=" + montantSalaireBrutKarama +
                ", montantSalaireBrutAutre=" + montantSalaireBrutAutre +
                ", montantChiffreAffaireTTC=" + montantChiffreAffaireTTC +
                ", tot=" + tot +
                ", cnss=" + cnss +
                ", ficheClientId=" + ficheClientId +
                ", ficheClientDesignation='" + ficheClientDesignation + '\'' +
                ", ficheClientMatriculeFiscale='" + ficheClientMatriculeFiscale + '\'' +
                ", ficheClientRegistreCommerce='" + ficheClientRegistreCommerce + '\'' +
                ", ficheClientDateCreation=" + ficheClientDateCreation +
                '}';
    }

    public void setCnss(BigDecimal cnss) {
        this.cnss = cnss;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CnssDTO cnssDTO = (CnssDTO) o;
        if (cnssDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cnssDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
