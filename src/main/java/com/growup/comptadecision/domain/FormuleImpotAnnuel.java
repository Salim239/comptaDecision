package com.growup.comptadecision.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * A ImpotMensuel.
 */
@Entity
@Table(name = "formule_impot_annuel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class FormuleImpotAnnuel extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "impot_annuel_enfant_id", nullable = false)
    private ImpotAnnuel impotAnnuelEnfant;

    @NotNull
    @JoinColumn(name = "impot_annuel_parent_id", nullable = false)
    @ManyToOne
    private ImpotAnnuel impotAnnuelParent;

    @Column(name = "coefficient")
    private Double coefficient = 1d;

    public ImpotAnnuel getImpotAnnuelEnfant() {
        return impotAnnuelEnfant;
    }

    public void setImpotAnnuelEnfant(ImpotAnnuel impotAnnuelEnfant) {
        this.impotAnnuelEnfant = impotAnnuelEnfant;
    }

    public ImpotAnnuel getImpotAnnuelParent() {
        return impotAnnuelParent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormuleImpotAnnuel that = (FormuleImpotAnnuel) o;
        return Objects.equals(impotAnnuelEnfant, that.impotAnnuelEnfant) &&
            Objects.equals(impotAnnuelParent, that.impotAnnuelParent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(impotAnnuelEnfant, impotAnnuelParent);
    }

    public void setImpotAnnuelParent(ImpotAnnuel impotAnnuelParent) {
        this.impotAnnuelParent = impotAnnuelParent;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
