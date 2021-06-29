package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Caisse client
 */
@ApiModel(description = "Caisse client")
@Entity
@Table(name = "caisse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Caisse extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant_total", precision = 21, scale = 2)
    private BigDecimal montantTotal;

    @Column(name = "montant_report", precision = 21, scale = 2)
    private BigDecimal montantReport;

    @Column(name = "cloturee")
    private Boolean cloturee;

    @OneToMany(mappedBy = "caisse")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LigneCaisse> ligneCaisses = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("caisses")
    private FicheClient ficheClient;

    public Boolean getCloturee() {
        return cloturee;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public Caisse montantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
        return this;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getMontantReport() {
        return montantReport;
    }

    public Caisse montantReport(BigDecimal montantReport) {
        this.montantReport = montantReport;
        return this;
    }

    public void setMontantReport(BigDecimal montantReport) {
        this.montantReport = montantReport;
    }

    public Boolean isCloturee() {
        return cloturee;
    }

    public Caisse cloturee(Boolean cloturee) {
        this.cloturee = cloturee;
        return this;
    }

    public void setCloturee(Boolean cloturee) {
        this.cloturee = cloturee;
    }

    public Set<LigneCaisse> getLigneCaisses() {
        return ligneCaisses;
    }

    public Caisse ligneCaisses(Set<LigneCaisse> ligneCaisses) {
        this.ligneCaisses = ligneCaisses;
        return this;
    }

    public Caisse addLigneCaisse(LigneCaisse ligneCaisse) {
        this.ligneCaisses.add(ligneCaisse);
        ligneCaisse.setCaisse(this);
        return this;
    }

    public Caisse removeLigneCaisse(LigneCaisse ligneCaisse) {
        this.ligneCaisses.remove(ligneCaisse);
        ligneCaisse.setCaisse(null);
        return this;
    }

    public void setLigneCaisses(Set<LigneCaisse> ligneCaisses) {
        this.ligneCaisses = ligneCaisses;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public Caisse ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Caisse)) {
            return false;
        }
        return id != null && id.equals(((Caisse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Caisse{" +
            "id=" + getId() +
            ", montantTotal=" + getMontantTotal() +
            ", montantReport=" + getMontantReport() +
            ", cloturee='" + isCloturee() + "'" +
            "}";
    }
}
