package com.growup.comptadecision.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CabinetComptable entity.
 */
@ApiModel(description = "Cabinet Comptable")
public class CabinetComptableDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private Integer nombreLicense;

    /**
     * s'il s'agit de la fichie client du cabinet comptable lui même
     */
    @ApiModelProperty(value = "s'il s'agit de la fichie client du cabinet comptable lui même")

    private Long ficheClientCabinetId;
    /**
     * list des clients du cabinet comptable
     */
    @ApiModelProperty(value = "list des clients du cabinet comptable")

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

    public Integer getNombreLicense() {
        return nombreLicense;
    }

    public void setNombreLicense(Integer nombreLicense) {
        this.nombreLicense = nombreLicense;
    }

    public Long getFicheClientCabinetId() {
        return ficheClientCabinetId;
    }

    public void setFicheClientCabinetId(Long ficheClientId) {
        this.ficheClientCabinetId = ficheClientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CabinetComptableDTO cabinetComptableDTO = (CabinetComptableDTO) o;
        if (cabinetComptableDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cabinetComptableDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CabinetComptableDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", nombreLicense=" + getNombreLicense() +
            ", ficheClientCabinet=" + getFicheClientCabinetId() +
            "}";
    }
}
