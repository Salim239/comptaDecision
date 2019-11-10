package com.growup.comptadecision.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * A ImpotMensuel.
 */
@Entity
@Table(name = "impot_mensuel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class ImpotMensuel extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public ImpotMensuel() {
    }

    public ImpotMensuel(Long id) {
        this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "parent")
    private Boolean parent;

    @Column(name = "child")
    private Boolean child;

    @OneToMany(mappedBy = "impotMensuel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImpotMensuelDetail> impotMensuelDetails = new ArrayList<>();

    @OneToMany(mappedBy = "parentImpotMensuel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ImpotMensuel> childImpotMensuels = new ArrayList<>();

    @OneToMany(mappedBy = "impotMensuel")
    List<ImpotMensuelClient> impotMensuelClients = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private ImpotMensuel parentImpotMensuel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public Boolean getChild() {
        return child;
    }

    public void setChild(Boolean child) {
        this.child = child;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ImpotMensuelDetail> getImpotMensuelDetails() {
        return impotMensuelDetails;
    }

    public void setImpotMensuelDetails(List<ImpotMensuelDetail> impotMensuelDetails) {
        this.impotMensuelDetails = impotMensuelDetails;
    }

    public ImpotMensuel code(String code) {
        this.code = code;
        return this;
    }
    public ImpotMensuel libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public ImpotMensuel description(String description) {
        this.code = description;
        return this;
    }

    public void addImpotMensuelDetail(ImpotMensuelDetail impotMensuelDetail) {
        impotMensuelDetails.add(impotMensuelDetail);
        impotMensuelDetail.setImpotMensuel(this);
    }

    public void removeImpotMensuelDetail(ImpotMensuelDetail impotMensuelDetail) {
        impotMensuelDetails.remove(impotMensuelDetail);
        impotMensuelDetail.setImpotMensuel(null);
    }

    public ImpotMensuel getParentImpotMensuel() {
        return parentImpotMensuel;
    }

    public void setParentImpotMensuel(ImpotMensuel parentImpotMensuel) {
        this.parentImpotMensuel = parentImpotMensuel;
    }

    public List<ImpotMensuel> getChildImpotMensuels() {
        return childImpotMensuels;
    }

    public void setChildImpotMensuels(List<ImpotMensuel> childImpotMensuels) {
        this.childImpotMensuels = childImpotMensuels;
    }

    public List<ImpotMensuelClient> getImpotMensuelClients() {
        return impotMensuelClients;
    }

    public void setImpotMensuelClients(List<ImpotMensuelClient> impotMensuelClients) {
        this.impotMensuelClients = impotMensuelClients;
    }
}
