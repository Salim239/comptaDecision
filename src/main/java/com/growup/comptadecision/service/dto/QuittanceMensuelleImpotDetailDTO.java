package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the QuittanceMensuelleImpotDetail entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpotDetailDTO implements Serializable {

    public QuittanceMensuelleImpotDetailDTO() {
    }

    public QuittanceMensuelleImpotDetailDTO(Long quittanceMensuelleImpotId, ImpotMensuelDTO impotMensuel, BigDecimal montantTotal) {
        this.quittanceMensuelleImpotId = quittanceMensuelleImpotId;
        this.impotMensuel = impotMensuel;
        this.montantTotal = montantTotal;
    }

    private Long id;

    private String libelle;

    private Boolean parent;

    private Boolean child;

    private BigDecimal montantTotal;

    private Long parentQuittanceMensuelleImpotDetailId;

    private String parentQuittanceMensuelleImpotDetailLibelle;

    private Long quittanceMensuelleImpotId;

    private ImpotMensuelDTO impotMensuel;

    private List<QuittanceMensuelleImpotSousDetailDTO> quittanceMensuelleImpotSousDetails = new ArrayList<>();

    private List<QuittanceMensuelleImpotDetailDTO> childQuittanceMensuelleImpotDetails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuittanceMensuelleImpotId() {
        return quittanceMensuelleImpotId;
    }

    public void setQuittanceMensuelleImpotId(Long quittanceMensuelleImpotId) {
        this.quittanceMensuelleImpotId = quittanceMensuelleImpotId;
    }

    public ImpotMensuelDTO getImpotMensuel() {
        return impotMensuel;
    }

    public void setImpotMensuel(ImpotMensuelDTO impotMensuel) {
        this.impotMensuel = impotMensuel;
    }

    public List<QuittanceMensuelleImpotSousDetailDTO> getQuittanceMensuelleImpotSousDetails() {
        return quittanceMensuelleImpotSousDetails;
    }

    public void setQuittanceMensuelleImpotSousDetails(List<QuittanceMensuelleImpotSousDetailDTO> quittanceMensuelleImpotSousDetails) {
        this.quittanceMensuelleImpotSousDetails = quittanceMensuelleImpotSousDetails;
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

    public Long getParentQuittanceMensuelleImpotDetailId() {
        return parentQuittanceMensuelleImpotDetailId;
    }

    public void setParentQuittanceMensuelleImpotDetailId(Long parentQuittanceMensuelleImpotDetailId) {
        this.parentQuittanceMensuelleImpotDetailId = parentQuittanceMensuelleImpotDetailId;
    }

    public String getParentQuittanceMensuelleImpotDetailLibelle() {
        return parentQuittanceMensuelleImpotDetailLibelle;
    }

    public void setParentQuittanceMensuelleImpotDetailLibelle(String parentQuittanceMensuelleImpotDetailLibelle) {
        this.parentQuittanceMensuelleImpotDetailLibelle = parentQuittanceMensuelleImpotDetailLibelle;
    }

    public List<QuittanceMensuelleImpotDetailDTO> getChildQuittanceMensuelleImpotDetails() {
        return childQuittanceMensuelleImpotDetails;
    }

    public void setChildQuittanceMensuelleImpotDetails(List<QuittanceMensuelleImpotDetailDTO> childQuittanceMensuelleImpotDetails) {
        this.childQuittanceMensuelleImpotDetails = childQuittanceMensuelleImpotDetails;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }
}
