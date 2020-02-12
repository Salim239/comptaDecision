package com.growup.comptadecision.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + "/parameterized");
    public static final URI ENTITY_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/entity-not-found");
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");
    public static final URI EMAIL_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/email-not-found");

    public static final String ERR_ANNEE_NON_VALIDE = "error.quittance.anneeNonValide";
    public static final String ERR_MOIS_NON_VALIDE = "error.quittance.moisNonValide";
    //Quittance errors
    public static final String ERR_QUITTANCE_RECTIFICATIVE_SANS_QUITTANCE_INITIALE = "error.quittance.qittanceRectificativeSansQuittanceInitiale";
    public static final String ERR_QUITTANCE_PRECEDENTE_NON_VALIDE = "error.quittance.quittancePrecedenteNonValide";
    public static final String ERR_QUITTANCE_INITIALE_INEXISTANTE = "error.quittance.quittanceInitialeInexistante";
    public static final String ERR_QUITTANCE_INITIALE_NON_VALIDE = "error.quittance.quittanceInitialeNonValidee";
    public static final String ERR_QUITTANCE_RECTIFICATIVE_INEXISTANTE = "error.quittance.quittanceRectificativeInexistante";
    public static final String ERR_QUITTANCE_PRECEDENTE_INEXISTANTE = "error.quittance.quittancePrecedenteInexistante";
    public static final String ERR_QUITTANCE_A_SUPPRIMER_INEXISTANTE = "error.quittance.quittanceASuprimerNonExistante";
    public static final String ERR_QUITTANCEA_SUPPRIMER_VALIDEE = "error.quittance.quittanceASupprimerValidee";
    public static final String ERR_QUITTANCE_RECTIFICATIVE_A_SUPPRIMER_SANS_QUITTANCE_INITIALE = "error.quittance.quittanceRectificativeAsupprimerPossedePasQuittanceInitiale";

    private ErrorConstants() {
    }
}
