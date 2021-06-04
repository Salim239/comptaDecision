package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeValeur;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the QuittanceMensuelleLigne entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleSousLigneDTO implements Serializable, Cloneable {

    public QuittanceMensuelleSousLigneDTO() {
    }

    private Long id;

    private Long quittanceMensuelleLigneId;

    private Long impotMensuelLigneId;

    private Integer impotMensuelLigneTriOrdre;

    private String impotMensuelLigneCode;

    private String impotMensuelLigneLibelle;

    private TypeValeur impotMensuelLigneTypeValeur = TypeValeur.TAUX;

    private Float impotMensuelLigneValeur = 1f;

    private Boolean impotMensuelLigneValeurModifiable;

    private String impotMensuelLigneDescription;

    private BigDecimal montantBase = BigDecimal.ZERO;

    private BigDecimal montantTotal = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuittanceMensuelleLigneId() {
        return quittanceMensuelleLigneId;
    }

    public void setQuittanceMensuelleLigneId(Long quittanceMensuelleLigneId) {
        this.quittanceMensuelleLigneId = quittanceMensuelleLigneId;
    }

    public Long getImpotMensuelLigneId() {
        return impotMensuelLigneId;
    }

    public void setImpotMensuelLigneId(Long impotMensuelLigneId) {
        this.impotMensuelLigneId = impotMensuelLigneId;
    }

    public Integer getImpotMensuelLigneTriOrdre() {
        return impotMensuelLigneTriOrdre;
    }

    public void setImpotMensuelLigneTriOrdre(Integer impotMensuelLigneTriOrdre) {
        this.impotMensuelLigneTriOrdre = impotMensuelLigneTriOrdre;
    }

    public String getImpotMensuelLigneCode() {
        return impotMensuelLigneCode;
    }

    public void setImpotMensuelLigneCode(String impotMensuelLigneCode) {
        this.impotMensuelLigneCode = impotMensuelLigneCode;
    }

    public String getImpotMensuelLigneLibelle() {
        return impotMensuelLigneLibelle;
    }

    public void setImpotMensuelLigneLibelle(String impotMensuelLigneLibelle) {
        this.impotMensuelLigneLibelle = impotMensuelLigneLibelle;
    }

    public TypeValeur getImpotMensuelLigneTypeValeur() {
        return impotMensuelLigneTypeValeur;
    }

    public void setImpotMensuelLigneTypeValeur(TypeValeur impotMensuelLigneTypeValeur) {
        this.impotMensuelLigneTypeValeur = impotMensuelLigneTypeValeur;
    }

    public Boolean getImpotMensuelLigneValeurModifiable() {
        return impotMensuelLigneValeurModifiable;
    }

    public void setImpotMensuelLigneValeurModifiable(Boolean impotMensuelLigneValeurModifiable) {
        this.impotMensuelLigneValeurModifiable = impotMensuelLigneValeurModifiable;
    }

    public String getImpotMensuelLigneDescription() {
        return impotMensuelLigneDescription;
    }

    public void setImpotMensuelLigneDescription(String impotMensuelLigneDescription) {
        this.impotMensuelLigneDescription = impotMensuelLigneDescription;
    }

    public BigDecimal getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(BigDecimal montantBase) {
        this.montantBase = montantBase;
    }

    public Float getImpotMensuelLigneValeur() {
        return impotMensuelLigneValeur;
    }

    public void setImpotMensuelLigneValeur(Float impotMensuelLigneValeur) {
        this.impotMensuelLigneValeur = impotMensuelLigneValeur;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        QuittanceMensuelleSousLigneDTO cloneQuittanceMensuelleSousLigne = (QuittanceMensuelleSousLigneDTO) super.clone();
        cloneQuittanceMensuelleSousLigne.setQuittanceMensuelleLigneId(null);
        return cloneQuittanceMensuelleSousLigne;
    }
}
