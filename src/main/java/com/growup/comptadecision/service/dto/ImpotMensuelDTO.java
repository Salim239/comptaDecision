package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the ImpotMensuel entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class ImpotMensuelDTO implements Serializable {

    public ImpotMensuelDTO() {
    }

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private BigDecimal montant;

    private String description;

    private Boolean parent;

    private Boolean child;

    private List<ImpotMensuelDetailDTO> impotMensuelDetails = new ArrayList<>();

    private List<ImpotMensuelDTO> childImpotMensuels = new ArrayList<>();

    private Long parentImpotMensuelId;

    private String parentImpotMensuelLibelle;

    private Float valeur;

    private String typeValeur;

    private Boolean valeurModifiable;

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

    public Float getValeur() {
        return valeur;
    }

    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }

    public String getTypeValeur() {
        return typeValeur;
    }

    public void setTypeValeur(String typeValeur) {
        this.typeValeur = typeValeur;
    }

    public Boolean getValeurModifiable() {
        return valeurModifiable;
    }

    public void setValeurModifiable(Boolean valeurModifiable) {
        this.valeurModifiable = valeurModifiable;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public List<ImpotMensuelDTO> getChildImpotMensuels() {
        return childImpotMensuels;
    }

    public void setChildImpotMensuels(List<ImpotMensuelDTO> childImpotMensuels) {
        this.childImpotMensuels = childImpotMensuels;
    }
}
