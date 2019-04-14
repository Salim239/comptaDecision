package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A QuittanceMensuelleImpotLine.
 */
@Entity
@Table(name = "quittance_mensuelle_impot_line")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuittanceMensuelleImpotLine implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant_paye", precision = 10, scale = 2)
    private BigDecimal montantPaye;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quittance_mensuelle_impot_id", nullable = false)
//    @JsonIgnoreProperties("quittanceMensuelleImpotLines")
    private QuittanceMensuelleImpot quittanceMensuelleImpot;

    @ManyToOne
    private ImpotMensuelClient impotMensuelClient;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public QuittanceMensuelleImpotLine montantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
        return this;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public QuittanceMensuelleImpot getQuittanceMensuelleImpot() {
        return quittanceMensuelleImpot;
    }

    public QuittanceMensuelleImpotLine quittanceMensuelleImpot(QuittanceMensuelleImpot quittanceMensuelleImpot) {
        this.quittanceMensuelleImpot = quittanceMensuelleImpot;
        return this;
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



    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        QuittanceMensuelleImpotLine that = (QuittanceMensuelleImpotLine) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "QuittanceMensuelleImpotLine{" +
                "id=" + id +
                ", montantPaye=" + montantPaye +
                ", quittanceMensuelleImpot=" + quittanceMensuelleImpot +
                ", impotMensuelClient=" + impotMensuelClient +
                '}';
    }

}
