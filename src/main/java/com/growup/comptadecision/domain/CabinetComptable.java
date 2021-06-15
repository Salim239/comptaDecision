package com.growup.comptadecision.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Cabinet Comptable
 */
@Entity
@Table(name = "cabinet_comptable")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CabinetComptable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "nombre_license")
    private Integer nombreLicense;

    /**
     * s'il s'agit de la fichie client du cabinet comptable lui même
     */
    @OneToOne
    @JoinColumn(unique = true)
    private FicheClient ficheClientCabinet;

    /**
     * list des clients du cabinet comptable
     */
    @OneToMany(mappedBy = "cabinetComptable")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FicheClient> clients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public CabinetComptable code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNombreLicense() {
        return nombreLicense;
    }

    public CabinetComptable nombreLicense(Integer nombreLicense) {
        this.nombreLicense = nombreLicense;
        return this;
    }

    public void setNombreLicense(Integer nombreLicense) {
        this.nombreLicense = nombreLicense;
    }

    public FicheClient getFicheClientCabinet() {
        return ficheClientCabinet;
    }

    public CabinetComptable ficheClientCabinet(FicheClient ficheClient) {
        this.ficheClientCabinet = ficheClient;
        return this;
    }

    public void setFicheClientCabinet(FicheClient ficheClient) {
        this.ficheClientCabinet = ficheClient;
    }

    public Set<FicheClient> getClients() {
        return clients;
    }

    public CabinetComptable clients(Set<FicheClient> ficheClients) {
        this.clients = ficheClients;
        return this;
    }

    public CabinetComptable addClient(FicheClient ficheClient) {
        this.clients.add(ficheClient);
        ficheClient.setCabinetComptable(this);
        return this;
    }

    public CabinetComptable removeClient(FicheClient ficheClient) {
        this.clients.remove(ficheClient);
        ficheClient.setCabinetComptable(null);
        return this;
    }

    public void setClients(Set<FicheClient> ficheClients) {
        this.clients = ficheClients;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CabinetComptable)) {
            return false;
        }
        return id != null && id.equals(((CabinetComptable) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CabinetComptable{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", nombreLicense=" + getNombreLicense() +
            "}";
    }
}
