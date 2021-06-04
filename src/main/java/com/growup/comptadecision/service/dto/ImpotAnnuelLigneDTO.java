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
public class ImpotAnnuelLigneDTO implements Serializable {

    private Long id;

    private Long impotMensuelId;

    private String impotMensuelLibelle;

    private Long impotMensuelLigneId;

    private String impotMensuelLigneLibelle;

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

    public Long getImpotMensuelLigneId() {
        return impotMensuelLigneId;
    }

    public void setImpotMensuelLigneId(Long impotMensuelLigneId) {
        this.impotMensuelLigneId = impotMensuelLigneId;
    }

    public String getImpotMensuelLigneLibelle() {
        return impotMensuelLigneLibelle;
    }

    public void setImpotMensuelLigneLibelle(String impotMensuelLigneLibelle) {
        this.impotMensuelLigneLibelle = impotMensuelLigneLibelle;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

}
