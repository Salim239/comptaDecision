package com.growup.comptadecision.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * A QuittanceMensuelleLigne.
 */
@Entity
@Table(name = "quittance_mensuelle_sous_ligne")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleSousLigne extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public QuittanceMensuelleSousLigne() {
    }

    public QuittanceMensuelleSousLigne(QuittanceMensuelleLigne quittanceMensuelleLigne, ImpotMensuelLigne impotMensuelLigne, BigDecimal montantbase) {
        this.quittanceMensuelleLigne = quittanceMensuelleLigne;
        this.impotMensuelLigne = impotMensuelLigne;
        this.montantBase = montantbase;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant_base")
    private BigDecimal montantBase;

    @Column(name = "montant_total")
    private BigDecimal montantTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuittanceMensuelleLigne quittanceMensuelleLigne;

    @ManyToOne(fetch = FetchType.LAZY)
    private ImpotMensuelLigne impotMensuelLigne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(BigDecimal montantBase) {
        this.montantBase = montantBase;
    }

    public QuittanceMensuelleLigne getQuittanceMensuelleLigne() {
        return quittanceMensuelleLigne;
    }

    public void setQuittanceMensuelleLigne(QuittanceMensuelleLigne quittanceMensuelleLigne) {
        this.quittanceMensuelleLigne = quittanceMensuelleLigne;
    }

    public ImpotMensuelLigne getImpotMensuelLigne() {
        return impotMensuelLigne;
    }

    public void setImpotMensuelLigne(ImpotMensuelLigne impotMensuelLigne) {
        this.impotMensuelLigne = impotMensuelLigne;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }
}

