package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the DeclarationAnnuelle entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class DeclarationAnnuelleLigneDTO implements Serializable {

    private Long id;
    private String code;
    private Integer triOrdre;
    private Boolean calcule;
    private BigDecimal montant;
    private BigDecimal montantCalcule;
    private String libelle;
    private String description;
    private Long impotAnnuelId;
    private Long declarationAnnuelleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTriOrdre() {
        return triOrdre;
    }

    public void setTriOrdre(Integer triOrdre) {
        this.triOrdre = triOrdre;
    }

    public Boolean getCalcule() {
        return calcule;
    }

    public void setCalcule(Boolean calcule) {
        this.calcule = calcule;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontantCalcule() {
        return montantCalcule;
    }

    public void setMontantCalcule(BigDecimal montantCalcule) {
        this.montantCalcule = montantCalcule;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getImpotAnnuelId() {
        return impotAnnuelId;
    }

    public void setImpotAnnuelId(Long impotAnnuelId) {
        this.impotAnnuelId = impotAnnuelId;
    }

    public Long getDeclarationAnnuelleId() {
        return declarationAnnuelleId;
    }

    public void setDeclarationAnnuelleId(Long declarationAnnuelleId) {
        this.declarationAnnuelleId = declarationAnnuelleId;
    }


}
