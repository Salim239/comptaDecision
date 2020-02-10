package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the ImpotAnnuel entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class ImpotAnnuelDetailDTO implements Serializable {

    private Long id;

    private Long impotMensuelId;

    private String impotMensuelLibelle;

    private Long impotMensuelDetailId;

    private String impotMensuelDetailLibelle;

    private Integer coefficient = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImpotMensuelId() {
        return impotMensuelId;
    }

    public void setImpotMensuelId(Long impotMensuelId) {
        this.impotMensuelId = impotMensuelId;
    }

    public String getImpotMensuelLibelle() {
        return impotMensuelLibelle;
    }

    public void setImpotMensuelLibelle(String impotMensuelLibelle) {
        this.impotMensuelLibelle = impotMensuelLibelle;
    }

    public Long getImpotMensuelDetailId() {
        return impotMensuelDetailId;
    }

    public void setImpotMensuelDetailId(Long impotMensuelDetailId) {
        this.impotMensuelDetailId = impotMensuelDetailId;
    }

    public String getImpotMensuelDetailLibelle() {
        return impotMensuelDetailLibelle;
    }

    public void setImpotMensuelDetailLibelle(String impotMensuelDetailLibelle) {
        this.impotMensuelDetailLibelle = impotMensuelDetailLibelle;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }
}
