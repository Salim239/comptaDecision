package com.growup.comptadecision.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class QuittanceMensuelleSousLigneRepositoryImpl implements QuittanceMensuelleSousLigneRepositoryCustom{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BigDecimal sumMontantBaseByFicheClientIdAndByAnneeAndByCodes(Long ficheClientId, Integer annee, List<String> codes) {

        String codesStr = "'" + String.join("','", codes) + "'";

        //Query on quittance detail
        String QUERY1 = "select sum(qisd.montant_base) " +
                "from quittance_mensuelle_sous_ligne qisd " +
                "join quittance_mensuelle_ligne qmid on qisd.quittance_mensuelle_ligne_id = qmid.id " +
                "join quittance_mensuelle qmi on qmid.quittance_mensuelle_id = qmi.id " +
                "join impot_mensuel_ligne imd on imd.id = qisd.impot_mensuel_ligne_id " +
                "where qmi.fiche_client_id = ?" +
                "and qmi.statut != 'ARCHIVE' " +
                "and qmi.annee = ? " +
                "and imd.code in ( "+ codesStr + ")";

        BigDecimal montantTotalLigne = jdbcTemplate.queryForObject(QUERY1, new Object[]{ ficheClientId, annee}, BigDecimal.class);

        //Query on child quittance detail
//        String QUERY2= "select sum(qisd.montant_base) " +
//                "from quittance_mensuelle_sous_ligne qisd " +
//                "join quittance_mensuelle_ligne child_qmid on qisd.quittance_mensuelle_ligne_id = child_qmid.id " +
//                "join quittance_mensuelle_ligne qmid on child_qmid.parent_quittance_mensuelle_ligne_id = qmid.id " +
//                "join quittance_mensuelle qmi on qmid.quittance_mensuelle_id = qmi.id " +
//                "join impot_mensuel_ligne imd on imd.id = qisd.impot_mensuel_ligne_id " +
//                "where qmi.fiche_client_id = ?" +
//                "and qmi.statut = 'ARCHIVE' " +
//                "and qmi.annee = ? " +
//                "and imd.code in ( "+ codesStr + ")";

        String QUERY2 = "select sum(qisd.montant_base) " +
            "from quittance_mensuelle_sous_ligne qisd " +
            "join quittance_mensuelle_ligne qmid on qisd.quittance_mensuelle_ligne_id = qmid.id " +
            "join quittance_mensuelle_ligne qmid2 on qmid2.id = qmid.parent_quittance_mensuelle_ligne_id " +
            "join quittance_mensuelle qmi on qmid2.quittance_mensuelle_id = qmi.id " +
            "join impot_mensuel_ligne imd on imd.id = qisd.impot_mensuel_ligne_id " +
            "where qmi.fiche_client_id = ? " +
            "and qmi.statut != 'ARCHIVE' " +
            "and qmi.annee = ? " +
            "and imd.code in ( "+ codesStr + ")";

        BigDecimal montantTotalSousLigne = jdbcTemplate.queryForObject(QUERY2, new Object[]{ ficheClientId, annee}, BigDecimal.class);

        BigDecimal montant1 = montantTotalLigne != null ? montantTotalLigne : BigDecimal.ZERO;
        BigDecimal montant2 = montantTotalSousLigne != null ? montantTotalSousLigne : BigDecimal.ZERO;
        return montant1.add(montant2);
    }
}
