package com.growup.comptadecision.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the QuittanceMensuelleImpotLine entity.
 */
public class QuittanceMensuelleImpotLineDTO implements Serializable {

    private Long id;

    private BigDecimal montantPaye;


    private Long quittanceMensuelleImpotId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Long getQuittanceMensuelleImpotId() {
        return quittanceMensuelleImpotId;
    }

    public void setQuittanceMensuelleImpotId(Long quittanceMensuelleImpotId) {
        this.quittanceMensuelleImpotId = quittanceMensuelleImpotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO = (QuittanceMensuelleImpotLineDTO) o;
        if (quittanceMensuelleImpotLineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quittanceMensuelleImpotLineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuittanceMensuelleImpotLineDTO{" +
            "id=" + getId() +
            ", montantPaye=" + getMontantPaye() +
            ", quittanceMensuelleImpot=" + getQuittanceMensuelleImpotId() +
            "}";
    }
}
