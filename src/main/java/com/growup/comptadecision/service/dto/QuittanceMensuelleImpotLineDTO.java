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

    private String quittanceMensuelleImpotMois;

    private String quittanceMensuelleImpotAnnee;

    private ImpotMensuelClientDTO impotMensuelClient;

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
    public String toString() {
        return "QuittanceMensuelleImpotLineDTO{" +
                "id=" + id +
                ", montantPaye=" + montantPaye +
                ", quittanceMensuelleImpotId=" + quittanceMensuelleImpotId +
                ", quittanceMensuelleImpotMois='" + quittanceMensuelleImpotMois + '\'' +
                ", quittanceMensuelleImpotAnnee='" + quittanceMensuelleImpotAnnee + '\'' +
                ", impotMensuelClient=" + impotMensuelClient +
                '}';
    }

    public String getQuittanceMensuelleImpotMois() {
        return quittanceMensuelleImpotMois;
    }

    public void setQuittanceMensuelleImpotMois(String quittanceMensuelleImpotMois) {
        this.quittanceMensuelleImpotMois = quittanceMensuelleImpotMois;
    }

    public String getQuittanceMensuelleImpotAnnee() {
        return quittanceMensuelleImpotAnnee;
    }

    public void setQuittanceMensuelleImpotAnnee(String quittanceMensuelleImpotAnnee) {
        this.quittanceMensuelleImpotAnnee = quittanceMensuelleImpotAnnee;
    }

    public ImpotMensuelClientDTO getImpotMensuelClient() {
        return impotMensuelClient;
    }

    public void setImpotMensuelClient(ImpotMensuelClientDTO impotMensuelClient) {
        this.impotMensuelClient = impotMensuelClient;
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

}
