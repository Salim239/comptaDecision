package com.growup.comptadecision.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * A ImpotMensuelClient. Un impot annuel peut être soit la somme d'impot mensuel, soit calculé via les autres impots mensuels
 */
@Entity
@Table(name = "impot_annuel_detail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class ImpotAnnuelDetail extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public ImpotAnnuelDetail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ImpotAnnuel impotAnnuel;

    @ManyToOne(fetch = FetchType.LAZY)
    private ImpotMensuelDetail impotMensuelDetail;

    private Float coefficient = 1f;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImpotAnnuel getImpotAnnuel() {
        return impotAnnuel;
    }

    public void setImpotAnnuel(ImpotAnnuel impotAnnuel) {
        this.impotAnnuel = impotAnnuel;
    }

    public ImpotMensuelDetail getImpotMensuelDetail() {
        return impotMensuelDetail;
    }

    public void setImpotMensuelDetail(ImpotMensuelDetail impotMensuelDetail) {
        this.impotMensuelDetail = impotMensuelDetail;
    }

    public Float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
    }
}
