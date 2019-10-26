package com.growup.comptadecision.domain;


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
@Table(name = "quittance_mensuelle_impot_sous_detail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpotSousDetail extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public QuittanceMensuelleImpotSousDetail() {
    }

    public QuittanceMensuelleImpotSousDetail(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail, ImpotMensuelDetail impotMensuelDetail, BigDecimal montantbase) {
        this.quittanceMensuelleImpotDetail = quittanceMensuelleImpotDetail;
        this.impotMensuelDetail = impotMensuelDetail;
        this.montantBase = montantbase;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant_base")
    private BigDecimal montantBase;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    private ImpotMensuelDetail impotMensuelDetail;

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

    public QuittanceMensuelleImpotDetail getQuittanceMensuelleImpotDetail() {
        return quittanceMensuelleImpotDetail;
    }

    public void setQuittanceMensuelleImpotDetail(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail) {
        this.quittanceMensuelleImpotDetail = quittanceMensuelleImpotDetail;
    }

    public ImpotMensuelDetail getImpotMensuelDetail() {
        return impotMensuelDetail;
    }

    public void setImpotMensuelDetail(ImpotMensuelDetail impotMensuelDetail) {
        this.impotMensuelDetail = impotMensuelDetail;
    }
}

