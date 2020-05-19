package com.growup.comptadecision.domain.enumeration;

/**
 * The CategorieClient enumeration.
 */
public enum CodeAlert {
    WARNING_QUITTANCE_PRECEDENTE_INEXISTANTE("Il n'existe pas de quittance créée pour le mois précédent: %s"),
    WARNING_DECLARATION_ANNUELLE_PRECEDENTE_INNEXISTANTE("Il n'existe pas de déclaration annuelle créée pour l'année précédente: %s"),
    WARNING_QUITTANCE_PRECEDENTE_NON_VALIDE("La quittance du mois précédent %s n'a pas encore été validée (numéro et date paiement non renseignés)"),
    WARNING_DECLARATION_ANNUELLE_PRECEDENTE_NON_VALIDE("La déclaration de l'année précédente %s n'a pas encore été validée (numéro et date paiement non renseignés)"),
    WARNING_QUITTANCE_INITIALE_NON_VALIDE("La quittance initiale %s n'a pas encore été validée (numéro et date paiement non renseignés)"),
    WARNING_DECLARATION_ANNUELLE_INITIALE_NON_VALIDE("La déclaration initiale %s n'a pas encore été validée (numéro et date paiement non renseignés)");

    // declaring private variable for getting values
    private String defaultMessage;

    // getter method
    public String getDefaultMessage()
    {
        return this.defaultMessage;
    }

    // enum constructor - cannot be public or protected
    private CodeAlert(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

}
