package com.growup.comptadecision.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ImpotMensuel entity.
 */
public class ImpotMensuelDetailDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer triOrdre;

    @NotNull
    private String code;

    private Float taux;

    @NotNull
    private String libelle;

    private String description;


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

    public Integer getTriOrdre() {
        return triOrdre;
    }

    public void setTriOrdre(Integer triOrdre) {
        this.triOrdre = triOrdre;
    }

    public Float getTaux() {
        return taux;
    }

    public void setTaux(Float taux) {
        this.taux = taux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImpotMensuelDetailDTO impotMensuelDTO = (ImpotMensuelDetailDTO) o;
        if (impotMensuelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), impotMensuelDTO.getId());
    }

    @Override
    public String toString() {
        return "ImpotMensuelDetailDTO{" +
                "id=" + id +
                ", triOrdre=" + triOrdre +
                ", code='" + code + '\'' +
                ", taux=" + taux +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
