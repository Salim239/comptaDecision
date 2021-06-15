package com.growup.comptadecision.service.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ApplicationUser entity.
 */
@ApiModel(description = "Application User")
public class ApplicationUserDTO implements Serializable {

    private Long id;

    private String matricule;

    @NotNull
    private String poste;

    private String dateEmbauche;


    private Long cabinetComptableId;

    private String cabinetComptableCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Long getCabinetComptableId() {
        return cabinetComptableId;
    }

    public void setCabinetComptableId(Long cabinetComptableId) {
        this.cabinetComptableId = cabinetComptableId;
    }

    public String getCabinetComptableCode() {
        return cabinetComptableCode;
    }

    public void setCabinetComptableCode(String cabinetComptableCode) {
        this.cabinetComptableCode = cabinetComptableCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApplicationUserDTO applicationUserDTO = (ApplicationUserDTO) o;
        if (applicationUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), applicationUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApplicationUserDTO{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", poste='" + getPoste() + "'" +
            ", dateEmbauche='" + getDateEmbauche() + "'" +
            ", cabinetComptable=" + getCabinetComptableId() +
            ", cabinetComptable='" + getCabinetComptableCode() + "'" +
            "}";
    }
}
