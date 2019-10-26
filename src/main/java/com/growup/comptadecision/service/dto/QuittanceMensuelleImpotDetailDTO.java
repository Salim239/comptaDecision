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

    public QuittanceMensuelleImpotDetailDTO(Long quittanceMensuelleImpotId, ImpotMensuelDTO impotMensuel, BigDecimal montantPaye) {
        this.quittanceMensuelleImpotId = quittanceMensuelleImpotId;
        this.impotMensuel = impotMensuel;
        this.montantPaye = montantPaye;
    }

    private Long id;

    private Long quittanceMensuelleImpotId;

    private ImpotMensuelDTO impotMensuel;

    private List<QuittanceMensuelleImpotSousDetailDTO> quittanceMensuelleImpotSousDetails = new ArrayList<>();

    private BigDecimal montantPaye;

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

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public List<QuittanceMensuelleImpotSousDetailDTO> getQuittanceMensuelleImpotSousDetails() {
        return quittanceMensuelleImpotSousDetails;
    }

    public void setQuittanceMensuelleImpotSousDetails(List<QuittanceMensuelleImpotSousDetailDTO> quittanceMensuelleImpotSousDetails) {
        this.quittanceMensuelleImpotSousDetails = quittanceMensuelleImpotSousDetails;
    }
}
