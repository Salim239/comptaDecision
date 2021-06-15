package com.growup.comptadecision.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the LigneCaisse entity.
 */
@ApiModel(description = "Ligne caisse")
public class LigneCaisseDTO implements Serializable {

    private Long id;

    /**
     * typeOperation 1 si Encaissement -1 si decaissement
     */
    @NotNull
    @ApiModelProperty(value = "typeOperation 1 si Encaissement -1 si decaissement", required = true)
    private Integer typeOperation;

    @NotNull
    private String libelle;

    private BigDecimal montantOperation;


    private Long quittanceMensuelleId;

    private Long declarationAnnuelleId;

    private Long cnssId;

    private Long caisseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(Integer typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getMontantOperation() {
        return montantOperation;
    }

    public void setMontantOperation(BigDecimal montantOperation) {
        this.montantOperation = montantOperation;
    }

    public Long getQuittanceMensuelleId() {
        return quittanceMensuelleId;
    }

    public void setQuittanceMensuelleId(Long quittanceMensuelleId) {
        this.quittanceMensuelleId = quittanceMensuelleId;
    }

    public Long getDeclarationAnnuelleId() {
        return declarationAnnuelleId;
    }

    public void setDeclarationAnnuelleId(Long declarationAnnuelleId) {
        this.declarationAnnuelleId = declarationAnnuelleId;
    }

    public Long getCnssId() {
        return cnssId;
    }

    public void setCnssId(Long cnssId) {
        this.cnssId = cnssId;
    }

    public Long getCaisseId() {
        return caisseId;
    }

    public void setCaisseId(Long caisseId) {
        this.caisseId = caisseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LigneCaisseDTO ligneCaisseDTO = (LigneCaisseDTO) o;
        if (ligneCaisseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ligneCaisseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LigneCaisseDTO{" +
            "id=" + getId() +
            ", typeOperation=" + getTypeOperation() +
            ", libelle='" + getLibelle() + "'" +
            ", montantOperation=" + getMontantOperation() +
            ", quittanceMensuelle=" + getQuittanceMensuelleId() +
            ", declarationAnnuelle=" + getDeclarationAnnuelleId() +
            ", cnss=" + getCnssId() +
            ", caisse=" + getCaisseId() +
            "}";
    }
}
