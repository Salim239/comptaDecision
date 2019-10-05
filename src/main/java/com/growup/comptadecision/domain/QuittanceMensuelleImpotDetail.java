package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.TypeValeur;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * A QuittanceMensuelleImpotDetail.
 */
@Entity
@Table(name = "quittance_mensuelle_impot_detail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpotDetail extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public QuittanceMensuelleImpotDetail() {
    }

    public QuittanceMensuelleImpotDetail(QuittanceMensuelleImpot quittanceMensuelleImpot, ImpotMensuelClient impotMensuelClient, ImpotMensuelDetail impotMensuelDetail) {
        this.quittanceMensuelleImpot = quittanceMensuelleImpot;
        this.impotMensuelClient = impotMensuelClient;
        this.impotMensuelDetail = impotMensuelDetail;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "impot_mensuel_valeur")
    private Float impotMensuelValeur;

    @Column(name = "impot_mensuel_type_valeur")
    private TypeValeur impotMensuelTypeValeur;

    @Column(name = "montant_base")
    private BigDecimal montantBase;

    @Column(name = "montant_total")
    private BigDecimal montantTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("impotMensuelClientDetails")
    private QuittanceMensuelleImpot quittanceMensuelleImpot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("impotMensuelClientDetails")
    private ImpotMensuelClient impotMensuelClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("impotMensuelClientDetails")
    private ImpotMensuelDetail impotMensuelDetail;

    public ImpotMensuelDetail getImpotMensuelDetail() {
        return impotMensuelDetail;
    }

    public void setImpotMensuelDetail(ImpotMensuelDetail impotMensuelDetail) {
        this.impotMensuelDetail = impotMensuelDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public QuittanceMensuelleImpot getQuittanceMensuelleImpot() {
        return quittanceMensuelleImpot;
    }

    public void setQuittanceMensuelleImpot(QuittanceMensuelleImpot quittanceMensuelleImpot) {
        this.quittanceMensuelleImpot = quittanceMensuelleImpot;
    }

    public ImpotMensuelClient getImpotMensuelClient() {
        return impotMensuelClient;
    }

    public void setImpotMensuelClient(ImpotMensuelClient impotMensuelClient) {
        this.impotMensuelClient = impotMensuelClient;
    }

    public QuittanceMensuelleImpotDetail montantTotal(BigDecimal montantTotal) {
        this.setMontantTotal(montantTotal);
        return this;
    }
}

