package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Chaque Utilisateur peut avoir une limitation d'accès
 * à une liste définie de clients de son cabinet comptable
 */
@ApiModel(description = "Chaque Utilisateur peut avoir une limitation d'accès à une liste définie de clients de son cabinet comptable")
@Entity
@Table(name = "application_user_fiche_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicationUserFicheClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("applicationUserFicheClients")
    private FicheClient ficheClient;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("applicationUserFicheClients")
    private ApplicationUser applicationUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public ApplicationUserFicheClient ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public ApplicationUserFicheClient applicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
        return this;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUserFicheClient)) {
            return false;
        }
        return id != null && id.equals(((ApplicationUserFicheClient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApplicationUserFicheClient{" +
            "id=" + getId() +
            "}";
    }
}
