package com.growup.comptadecision.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ImpotMensuelClient entity.
 */
public class ImpotMensuelClientDTO implements Serializable {

    private Long id;

    private Boolean applicable;

    private String ficheClientDesignation;

    private String ficheClientMatriculeFiscale;

    private String ficheClientRegistreCommerce;

    private Long impotMensuelId;

    private String impotMensuelLibelle;

    private String impotMensuelDescription;

    private Long ficheClientId;



    private Long quittanceMensuelleImpotLineId;

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

    public Long getQuittanceMensuelleImpotLineId() {
        return quittanceMensuelleImpotLineId;
    }

    public void setQuittanceMensuelleImpotLineId(Long quittanceMensuelleImpotLineId) {
        this.quittanceMensuelleImpotLineId = quittanceMensuelleImpotLineId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImpotMensuelClientDTO impotMensuelClientDTO = (ImpotMensuelClientDTO) o;
        if (impotMensuelClientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), impotMensuelClientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImpotMensuelClientDTO{" +
                "id=" + id +
                ", applicable=" + applicable +
                ", ficheClientDesignation='" + ficheClientDesignation + '\'' +
                ", ficheClientMatriculeFiscale='" + ficheClientMatriculeFiscale + '\'' +
                ", ficheClientRegistreCommerce='" + ficheClientRegistreCommerce + '\'' +
                ", impotMensuelId=" + impotMensuelId +
                ", impotMensuelLibelle='" + impotMensuelLibelle + '\'' +
                ", impotMensuelDescription='" + impotMensuelDescription + '\'' +
                ", ficheClientId=" + ficheClientId +
                ", quittanceMensuelleImpotLineId=" + quittanceMensuelleImpotLineId +
                '}';
    }
}
