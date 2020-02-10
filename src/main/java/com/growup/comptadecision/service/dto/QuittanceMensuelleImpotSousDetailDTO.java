package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeValeur;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the QuittanceMensuelleImpotDetail entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpotSousDetailDTO implements Serializable, Cloneable {

    public QuittanceMensuelleImpotSousDetailDTO() {
    }

    private Long id;

    private Long quittanceMensuelleImpotDetailId;

    private Long impotMensuelDetailId;

    private Integer impotMensuelDetailTriOrdre;

    private String impotMensuelDetailCode;

    private String impotMensuelDetailLibelle;

    private TypeValeur impotMensuelDetailTypeValeur = TypeValeur.TAUX;

    private Float impotMensuelDetailValeur = 1f;

    private Boolean impotMensuelDetailValeurModifiable;

    private String impotMensuelDetailDescription;

    private BigDecimal montantBase;

    private BigDecimal montantTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuittanceMensuelleImpotDetailId() {
        return quittanceMensuelleImpotDetailId;
    }

    public void setQuittanceMensuelleImpotDetailId(Long quittanceMensuelleImpotDetailId) {
        this.quittanceMensuelleImpotDetailId = quittanceMensuelleImpotDetailId;
    }

    public Long getImpotMensuelDetailId() {
        return impotMensuelDetailId;
    }

    public void setImpotMensuelDetailId(Long impotMensuelDetailId) {
        this.impotMensuelDetailId = impotMensuelDetailId;
    }

    public Integer getImpotMensuelDetailTriOrdre() {
        return impotMensuelDetailTriOrdre;
    }

    public void setImpotMensuelDetailTriOrdre(Integer impotMensuelDetailTriOrdre) {
        this.impotMensuelDetailTriOrdre = impotMensuelDetailTriOrdre;
    }

    public String getImpotMensuelDetailCode() {
        return impotMensuelDetailCode;
    }

    public void setImpotMensuelDetailCode(String impotMensuelDetailCode) {
        this.impotMensuelDetailCode = impotMensuelDetailCode;
    }

    public String getImpotMensuelDetailLibelle() {
        return impotMensuelDetailLibelle;
    }

    public void setImpotMensuelDetailLibelle(String impotMensuelDetailLibelle) {
        this.impotMensuelDetailLibelle = impotMensuelDetailLibelle;
    }

    public TypeValeur getImpotMensuelDetailTypeValeur() {
        return impotMensuelDetailTypeValeur;
    }

    public void setImpotMensuelDetailTypeValeur(TypeValeur impotMensuelDetailTypeValeur) {
        this.impotMensuelDetailTypeValeur = impotMensuelDetailTypeValeur;
    }

    public Boolean getImpotMensuelDetailValeurModifiable() {
        return impotMensuelDetailValeurModifiable;
    }

    public void setImpotMensuelDetailValeurModifiable(Boolean impotMensuelDetailValeurModifiable) {
        this.impotMensuelDetailValeurModifiable = impotMensuelDetailValeurModifiable;
    }

    public String getImpotMensuelDetailDescription() {
        return impotMensuelDetailDescription;
    }

    public void setImpotMensuelDetailDescription(String impotMensuelDetailDescription) {
        this.impotMensuelDetailDescription = impotMensuelDetailDescription;
    }

    public BigDecimal getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(BigDecimal montantBase) {
        this.montantBase = montantBase;
    }

    public Float getImpotMensuelDetailValeur() {
        return impotMensuelDetailValeur;
    }

    public void setImpotMensuelDetailValeur(Float impotMensuelDetailValeur) {
        this.impotMensuelDetailValeur = impotMensuelDetailValeur;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        QuittanceMensuelleImpotSousDetailDTO cloneQuittanceMensuelleImpotSousDetail = (QuittanceMensuelleImpotSousDetailDTO) super.clone();
        cloneQuittanceMensuelleImpotSousDetail.setQuittanceMensuelleImpotDetailId(null);
        return cloneQuittanceMensuelleImpotSousDetail;
    }
}
