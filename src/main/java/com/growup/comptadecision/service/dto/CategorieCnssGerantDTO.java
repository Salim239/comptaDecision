package com.growup.comptadecision.service.dto;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Region.
 */
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class CategorieCnssGerantDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    @NotNull
    private Double montantCotisationCnss;

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

    public Double getMontantCotisationCnss() {
        return montantCotisationCnss;
    }

    public void setMontantCotisationCnss(Double montantCotisationCnss) {
        this.montantCotisationCnss = montantCotisationCnss;
    }

}
