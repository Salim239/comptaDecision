package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the CabinetComptable entity. This class is used in CabinetComptableResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /cabinet-comptables?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CabinetComptableCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private IntegerFilter nombreLicense;

    private LongFilter ficheClientCabinetId;

    private LongFilter clientId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public IntegerFilter getNombreLicense() {
        return nombreLicense;
    }

    public void setNombreLicense(IntegerFilter nombreLicense) {
        this.nombreLicense = nombreLicense;
    }

    public LongFilter getFicheClientCabinetId() {
        return ficheClientCabinetId;
    }

    public void setFicheClientCabinetId(LongFilter ficheClientCabinetId) {
        this.ficheClientCabinetId = ficheClientCabinetId;
    }

    public LongFilter getClientId() {
        return clientId;
    }

    public void setClientId(LongFilter clientId) {
        this.clientId = clientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CabinetComptableCriteria that = (CabinetComptableCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(nombreLicense, that.nombreLicense) &&
            Objects.equals(ficheClientCabinetId, that.ficheClientCabinetId) &&
            Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        nombreLicense,
        ficheClientCabinetId,
        clientId
        );
    }

    @Override
    public String toString() {
        return "CabinetComptableCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (nombreLicense != null ? "nombreLicense=" + nombreLicense + ", " : "") +
                (ficheClientCabinetId != null ? "ficheClientCabinetId=" + ficheClientCabinetId + ", " : "") +
                (clientId != null ? "clientId=" + clientId + ", " : "") +
            "}";
    }

}
