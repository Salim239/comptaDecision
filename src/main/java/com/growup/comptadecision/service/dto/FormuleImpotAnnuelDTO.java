package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the ImpotAnnuel entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class FormuleImpotAnnuelDTO implements Serializable {

    public FormuleImpotAnnuelDTO() {
    }

    private Long id;

    @NotNull
    private Long impotAnnuelParentId;

    @NotNull
    private String impotAnnuelParentLibelle;

    @NotNull
    private Long impotAnnuelEnfantId;

    @NotNull
    private String impotAnnuelEnfantLibelle;

    private Double coefficient = 1d;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImpotAnnuelParentId() {
        return impotAnnuelParentId;
    }

    public void setImpotAnnuelParentId(Long impotAnnuelParentId) {
        this.impotAnnuelParentId = impotAnnuelParentId;
    }

    public String getImpotAnnuelParentLibelle() {
        return impotAnnuelParentLibelle;
    }

    public void setImpotAnnuelParentLibelle(String impotAnnuelParentLibelle) {
        this.impotAnnuelParentLibelle = impotAnnuelParentLibelle;
    }

    public Long getImpotAnnuelEnfantId() {
        return impotAnnuelEnfantId;
    }

    public void setImpotAnnuelEnfantId(Long impotAnnuelEnfantId) {
        this.impotAnnuelEnfantId = impotAnnuelEnfantId;
    }

    public String getImpotAnnuelEnfantLibelle() {
        return impotAnnuelEnfantLibelle;
    }

    public void setImpotAnnuelEnfantLibelle(String impotAnnuelEnfantLibelle) {
        this.impotAnnuelEnfantLibelle = impotAnnuelEnfantLibelle;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
}
