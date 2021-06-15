package com.growup.comptadecision.service.dto;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ApplicationUserFicheClient entity.
 */
@ApiModel(description = "Chaque Utilisateur peut avoir une limitation d'accès à une liste définie de clients de son cabinet comptable")
public class ApplicationUserFicheClientDTO implements Serializable {

    private Long id;


    private Long ficheClientId;

    private String ficheClientDesignation;

    private Long applicationUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApplicationUserFicheClientDTO applicationUserFicheClientDTO = (ApplicationUserFicheClientDTO) o;
        if (applicationUserFicheClientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), applicationUserFicheClientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApplicationUserFicheClientDTO{" +
            "id=" + getId() +
            ", ficheClient=" + getFicheClientId() +
            ", ficheClient='" + getFicheClientDesignation() + "'" +
            ", applicationUser=" + getApplicationUserId() +
            "}";
    }
}
