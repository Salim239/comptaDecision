package com.growup.comptadecision.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the QuittanceMensuelleImpotDetail entity.
 */
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class QuittanceMensuelleImpotSousDetailDTO implements Serializable {

    public QuittanceMensuelleImpotSousDetailDTO() {
    }

    public QuittanceMensuelleImpotSousDetailDTO(Long quittanceMensuelleImpotDetailId, ImpotMensuelDetailDTO impotMensuelDetail,
                                                BigDecimal montantBase, BigDecimal montantTotal) {
        this.quittanceMensuelleImpotDetailId = quittanceMensuelleImpotDetailId;
        this.impotMensuelDetail = impotMensuelDetail;
        this.montantBase = montantBase;
        this.montantTotal = montantTotal;
    }

    private Long id;

    private Long quittanceMensuelleImpotDetailId;

    private ImpotMensuelDetailDTO impotMensuelDetail;

    private BigDecimal montantBase;

    private BigDecimal montantTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuittanceMensuelleImpotDetailId() {
        return quittanceMensuelleImpotDetailId;
    }

    public void setQuittanceMensuelleImpotDetailId(Long quittanceMensuelleImpotDetailId) {
        this.quittanceMensuelleImpotDetailId = quittanceMensuelleImpotDetailId;
    }

    public ImpotMensuelDetailDTO getImpotMensuelDetail() {
        return impotMensuelDetail;
    }

    public void setImpotMensuelDetail(ImpotMensuelDetailDTO impotMensuelDetail) {
        this.impotMensuelDetail = impotMensuelDetail;
    }

    public BigDecimal getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(BigDecimal montantBase) {
        this.montantBase = montantBase;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }
}
