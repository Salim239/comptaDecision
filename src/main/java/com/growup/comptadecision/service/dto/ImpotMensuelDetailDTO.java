package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeValeur;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * A DTO for the ImpotMensuel entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class ImpotMensuelDetailDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer triOrdre;

    @NotNull
    private String code;

    private Float valeur;

    @Null
    private TypeValeur typeValeur;

    private Boolean valeurModifiable;

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

    public Float getValeur() {
        return valeur;
    }

    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }

    public TypeValeur getTypeValeur() {
        return typeValeur;
    }

    public void setTypeValeur(TypeValeur typeValeur) {
        this.typeValeur = typeValeur;
    }

    public Boolean getValeurModifiable() {
        return valeurModifiable;
    }

    public void setValeurModifiable(Boolean valeurModifiable) {
        this.valeurModifiable = valeurModifiable;
    }
}
