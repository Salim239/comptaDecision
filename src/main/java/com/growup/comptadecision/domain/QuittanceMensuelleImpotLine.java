package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToMany(mappedBy = "quittanceMensuelleImpotLine")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ImpotMensuelClient> impotMensuelClients = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("quittanceMensuelleImpotLines")
    private QuittanceMensuelleImpot quittanceMensuelleImpot;

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

    public Set<ImpotMensuelClient> getImpotMensuelClients() {
        return impotMensuelClients;
    }

    public QuittanceMensuelleImpotLine impotMensuelClients(Set<ImpotMensuelClient> impotMensuelClients) {
        this.impotMensuelClients = impotMensuelClients;
        return this;
    }

    public QuittanceMensuelleImpotLine addImpotMensuelClient(ImpotMensuelClient impotMensuelClient) {
        this.impotMensuelClients.add(impotMensuelClient);
        impotMensuelClient.setQuittanceMensuelleImpotLine(this);
        return this;
    }

    public QuittanceMensuelleImpotLine removeImpotMensuelClient(ImpotMensuelClient impotMensuelClient) {
        this.impotMensuelClients.remove(impotMensuelClient);
        impotMensuelClient.setQuittanceMensuelleImpotLine(null);
        return this;
    }

    public void setImpotMensuelClients(Set<ImpotMensuelClient> impotMensuelClients) {
        this.impotMensuelClients = impotMensuelClients;
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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuittanceMensuelleImpotLine quittanceMensuelleImpotLine = (QuittanceMensuelleImpotLine) o;
        if (quittanceMensuelleImpotLine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quittanceMensuelleImpotLine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuittanceMensuelleImpotLine{" +
            "id=" + getId() +
            ", montantPaye=" + getMontantPaye() +
            "}";
    }
}
