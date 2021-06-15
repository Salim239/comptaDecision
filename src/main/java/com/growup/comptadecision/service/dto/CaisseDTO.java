package com.growup.comptadecision.service.dto;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Caisse entity.
 */
@ApiModel(description = "Caisse client")
public class CaisseDTO implements Serializable {

    private Long id;

    private BigDecimal montantTotal;

    private BigDecimal montantReport;

    private Boolean cloturee;


    private Long ficheClientId;

    private String ficheClientDesignation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getMontantReport() {
        return montantReport;
    }

    public void setMontantReport(BigDecimal montantReport) {
        this.montantReport = montantReport;
    }

    public Boolean isCloturee() {
        return cloturee;
    }

    public void setCloturee(Boolean cloturee) {
        this.cloturee = cloturee;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public String getFicheClientDesignation() {
        return ficheClientDesignation;
    }

    public void setFicheClientDesignation(String ficheClientDesignation) {
        this.ficheClientDesignation = ficheClientDesignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaisseDTO caisseDTO = (CaisseDTO) o;
        if (caisseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caisseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaisseDTO{" +
            "id=" + getId() +
            ", montantTotal=" + getMontantTotal() +
            ", montantReport=" + getMontantReport() +
            ", cloturee='" + isCloturee() + "'" +
            ", ficheClient=" + getFicheClientId() +
            ", ficheClient='" + getFicheClientDesignation() + "'" +
            "}";
    }
}
