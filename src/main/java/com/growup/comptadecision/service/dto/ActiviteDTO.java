package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the Activite entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class ActiviteDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private Long  secteurActiviteId;
    private String secteurActiviteLibelle;

    private String description;

    public Long getSecteurActiviteId() {
        return secteurActiviteId;
    }

    public void setSecteurActiviteId(Long secteurActiviteId) {
        this.secteurActiviteId = secteurActiviteId;
    }

    public String getSecteurActiviteLibelle() {
        return secteurActiviteLibelle;
    }

    public void setSecteurActiviteLibelle(String secteurActiviteLibelle) {
        this.secteurActiviteLibelle = secteurActiviteLibelle;
    }

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

}
