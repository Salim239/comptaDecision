package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

/**
 * A ImpotMensuelClient.
 */
@Entity
@Table(name = "impot_mensuel_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ImpotMensuelClient extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "applicable")
    private Boolean applicable = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("impotMensuelClients")
    private FicheClient ficheClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("impotMensuelClients")
    private ImpotMensuel impotMensuel;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isApplicable() {
        return applicable;
    }

    public ImpotMensuelClient applicable(Boolean applicable) {
        this.applicable = applicable;
        return this;
    }

    public void setApplicable(Boolean applicable) {
        this.applicable = applicable;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public ImpotMensuelClient ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }

    public ImpotMensuel getImpotMensuel() {
        return impotMensuel;
    }

    public ImpotMensuelClient impotMensuel(ImpotMensuel impotMensuel) {
        this.impotMensuel = impotMensuel;
        return this;
    }

    public void setImpotMensuel(ImpotMensuel impotMensuel) {
        this.impotMensuel = impotMensuel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public String toString() {
        return "ImpotMensuelClient{" +
                "id=" + id +
                ", applicable=" + applicable +
                ", ficheClient=" + ficheClient +
                ", impotMensuel=" + impotMensuel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImpotMensuelClient impotMensuelClient = (ImpotMensuelClient) o;
        if (impotMensuelClient.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), impotMensuelClient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
