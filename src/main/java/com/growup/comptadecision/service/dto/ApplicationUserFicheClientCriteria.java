package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the ApplicationUserFicheClient entity. This class is used in ApplicationUserFicheClientResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /application-user-fiche-clients?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ApplicationUserFicheClientCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter ficheClientId;

    private LongFilter applicationUserId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(LongFilter ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public LongFilter getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(LongFilter applicationUserId) {
        this.applicationUserId = applicationUserId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ApplicationUserFicheClientCriteria that = (ApplicationUserFicheClientCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ficheClientId, that.ficheClientId) &&
            Objects.equals(applicationUserId, that.applicationUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ficheClientId,
        applicationUserId
        );
    }

    @Override
    public String toString() {
        return "ApplicationUserFicheClientCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
                (applicationUserId != null ? "applicationUserId=" + applicationUserId + ", " : "") +
            "}";
    }

}
