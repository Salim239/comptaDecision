package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the ImpotMensuelClient entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class ImpotMensuelClientDTO implements Serializable {

    private Long id;

    private Boolean applicable;

    private String ficheClientDesignation;

    private String ficheClientMatriculeFiscale;

    private String ficheClientRegistreCommerce;

    private Long impotMensuelId;

    private String impotMensuelLibelle;

    private String impotMensuelDescription;

    private Boolean impotMensuelParent;

    private Boolean impotMensuelChild;

    private Long impotMensuelParentId;

    private Long ficheClientId;

    private Long quittanceMensuelleLigneId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isApplicable() {
        return applicable;
    }

    public void setApplicable(Boolean applicable) {
        this.applicable = applicable;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public Long getImpotMensuelId() {
        return impotMensuelId;
    }

    public void setImpotMensuelId(Long impotMensuelId) {
        this.impotMensuelId = impotMensuelId;
    }

    public Long getQuittanceMensuelleLigneId() {
        return quittanceMensuelleLigneId;
    }

    public void setQuittanceMensuelleLigneId(Long quittanceMensuelleLigneId) {
        this.quittanceMensuelleLigneId = quittanceMensuelleLigneId;
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

    public String getImpotMensuelLibelle() {
        return impotMensuelLibelle;
    }

    public void setImpotMensuelLibelle(String impotMensuelLibelle) {
        this.impotMensuelLibelle = impotMensuelLibelle;
    }

    public String getImpotMensuelDescription() {
        return impotMensuelDescription;
    }

    public void setImpotMensuelDescription(String impotMensuelDescription) {
        this.impotMensuelDescription = impotMensuelDescription;
    }

    public Boolean getApplicable() {
        return applicable;
    }

    public Boolean getImpotMensuelParent() {
        return impotMensuelParent;
    }

    public void setImpotMensuelParent(Boolean impotMensuelParent) {
        this.impotMensuelParent = impotMensuelParent;
    }

    public Boolean getImpotMensuelChild() {
        return impotMensuelChild;
    }

    public void setImpotMensuelChild(Boolean impotMensuelChild) {
        this.impotMensuelChild = impotMensuelChild;
    }

    public Long getImpotMensuelParentId() {
        return impotMensuelParentId;
    }

    public void setImpotMensuelParentId(Long impotMensuelParentId) {
        this.impotMensuelParentId = impotMensuelParentId;
    }
}
