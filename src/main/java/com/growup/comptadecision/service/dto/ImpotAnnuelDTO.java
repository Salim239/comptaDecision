package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the ImpotAnnuel entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class ImpotAnnuelDTO implements Serializable {

    public ImpotAnnuelDTO() {
    }

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    private Boolean calcule = Boolean.FALSE;

    @NotNull
    private Integer triOrdre;

    private List<ImpotAnnuelLigneDTO> impotAnnuelLignes = new ArrayList<>();

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

    public Boolean getCalcule() {
        return calcule;
    }

    public void setCalcule(Boolean calcule) {
        this.calcule = calcule;
    }

    public List<ImpotAnnuelLigneDTO> getImpotAnnuelLignes() {
        return impotAnnuelLignes;
    }

    public void setImpotAnnuelLignes(List<ImpotAnnuelLigneDTO> impotAnnuelLignes) {
        this.impotAnnuelLignes = impotAnnuelLignes;
    }

    public Integer getTriOrdre() {
        return triOrdre;
    }

    public void setTriOrdre(Integer triOrdre) {
        this.triOrdre = triOrdre;
    }


}
