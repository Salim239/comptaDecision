package com.growup.comptadecision.repository.custom;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sh on 21/09/2016.
 */
public interface QuittanceMensuelleSousLigneRepositoryCustom {

    BigDecimal sumMontantBaseByFicheClientIdAndByAnneeAndByCodes(Long ficheClientId, Integer annee, List<String> codes);

}
