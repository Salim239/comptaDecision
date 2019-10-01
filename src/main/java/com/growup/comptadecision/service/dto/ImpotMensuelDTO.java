package com.growup.comptadecision.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the ImpotMensuel entity.
 */
public class ImpotMensuelDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    private Boolean parent;

    private Boolean child;

    private List<ImpotMensuelDetailDTO> impotMensuelDetails = new ArrayList<>();

    private Long parentImpotMensuelId;

    private String parentImpotMensuelLibelle;

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

    public Boolean getChild() {
        return child;
    }

    public void setChild(Boolean child) {
        this.child = child;
    }

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public Long getParentImpotMensuelId() {
        return parentImpotMensuelId;
    }

    @Override
    public String toString() {
        return "ImpotMensuelDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", child=" + child +
                ", impotMensuelDetails=" + impotMensuelDetails +
                ", parentImpotMensuelId=" + parentImpotMensuelId +
                ", parentImpotMensuelLibelle='" + parentImpotMensuelLibelle + '\'' +
                '}';
    }

    public void setParentImpotMensuelId(Long parentImpotMensuelId) {
        this.parentImpotMensuelId = parentImpotMensuelId;
    }

    public String getParentImpotMensuelLibelle() {
        return parentImpotMensuelLibelle;
    }

    public void setParentImpotMensuelLibelle(String parentImpotMensuelLibelle) {
        this.parentImpotMensuelLibelle = parentImpotMensuelLibelle;
    }

    public List<ImpotMensuelDetailDTO> getImpotMensuelDetails() {
        return impotMensuelDetails;
    }

    public void setImpotMensuelDetails(List<ImpotMensuelDetailDTO> impotMensuelDetails) {
        this.impotMensuelDetails = impotMensuelDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImpotMensuelDTO impotMensuelDTO = (ImpotMensuelDTO) o;
        if (impotMensuelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), impotMensuelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
