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
public class QuittanceMensuelleImpotDetailDTO implements Serializable {

    private Long id;

    private ImpotMensuelClientDTO impotMensuelClient;

    private QuittanceMensuelleImpotDTO quittanceMensuelleImpot;

    private ImpotMensuelDetailDTO impotMensuelDetail;

    private Float impotMensuelValeur;

    private TypeValeur impotMensuelTypeValeur;

    private BigDecimal montantBase;

    private BigDecimal montantTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImpotMensuelDetailDTO getImpotMensuelDetail() {
        return impotMensuelDetail;
    }

    public void setImpotMensuelDetail(ImpotMensuelDetailDTO impotMensuelDetail) {
        this.impotMensuelDetail = impotMensuelDetail;
    }

    public ImpotMensuelClientDTO getImpotMensuelClient() {
        return impotMensuelClient;
    }

    public void setImpotMensuelClient(ImpotMensuelClientDTO impotMensuelClient) {
        this.impotMensuelClient = impotMensuelClient;
    }

    public QuittanceMensuelleImpotDTO getQuittanceMensuelleImpot() {
        return quittanceMensuelleImpot;
    }

    public void setQuittanceMensuelleImpot(QuittanceMensuelleImpotDTO quittanceMensuelleImpot) {
        this.quittanceMensuelleImpot = quittanceMensuelleImpot;
    }

    public Float getImpotMensuelValeur() {
        return impotMensuelValeur;
    }

    public void setImpotMensuelValeur(Float impotMensuelValeur) {
        this.impotMensuelValeur = impotMensuelValeur;
    }

    public TypeValeur getImpotMensuelTypeValeur() {
        return impotMensuelTypeValeur;
    }

    public void setImpotMensuelTypeValeur(TypeValeur impotMensuelTypeValeur) {
        this.impotMensuelTypeValeur = impotMensuelTypeValeur;
    }

    public BigDecimal getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(BigDecimal montantBase) {
        this.montantBase = montantBase;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }
}
