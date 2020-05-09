package com.growup.comptadecision.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class QuittanceMensuelleImpotSousDetailRepositoryImpl implements QuittanceMensuelleImpotSousDetailRepositoryCustom{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BigDecimal sumMontantBaseByFicheClientIdAndByAnneeAndByCodes(Long ficheClientId, Integer annee, List<String> codes) {

        String codesStr = "'" + String.join("','", codes) + "'";

        //Query on quittance detail
        String QUERY1 = "select sum(qisd.montant_base) " +
                "from quittance_mensuelle_impot_sous_detail qisd " +
                "join quittance_mensuelle_impot_detail qmid on qisd.quittance_mensuelle_impot_detail_id = qmid.id " +
                "join quittance_mensuelle_impot qmi on qmid.quittance_mensuelle_impot_id = qmi.id " +
                "join impot_mensuel_detail imd on imd.id = qisd.impot_mensuel_detail_id " +
                "where qmi.fiche_client_id = ?" +
                "and qmi.statut != 'ARCHIVE' " +
                "and qmi.annee = ? " +
                "and imd.code in ( "+ codesStr + ")";

        BigDecimal montantTotalDetail = jdbcTemplate.queryForObject(QUERY1, new Object[]{ ficheClientId, annee}, BigDecimal.class);

        //Query on child quittance detail
//        String QUERY2= "select sum(qisd.montant_base) " +
//                "from quittance_mensuelle_impot_sous_detail qisd " +
//                "join quittance_mensuelle_impot_detail child_qmid on qisd.quittance_mensuelle_impot_detail_id = child_qmid.id " +
//                "join quittance_mensuelle_impot_detail qmid on child_qmid.parent_quittance_mensuelle_impot_detail_id = qmid.id " +
//                "join quittance_mensuelle_impot qmi on qmid.quittance_mensuelle_impot_id = qmi.id " +
//                "join impot_mensuel_detail imd on imd.id = qisd.impot_mensuel_detail_id " +
//                "where qmi.fiche_client_id = ?" +
//                "and qmi.statut = 'ARCHIVE' " +
//                "and qmi.annee = ? " +
//                "and imd.code in ( "+ codesStr + ")";

        String QUERY2 = "select sum(qisd.montant_base) " +
            "from quittance_mensuelle_impot_sous_detail qisd " +
            "join quittance_mensuelle_impot_detail qmid on qisd.quittance_mensuelle_impot_detail_id = qmid.id " +
            "join quittance_mensuelle_impot_detail qmid2 on qmid2.id = qmid.parent_quittance_mensuelle_impot_detail_id " +
            "join quittance_mensuelle_impot qmi on qmid2.quittance_mensuelle_impot_id = qmi.id " +
            "join impot_mensuel_detail imd on imd.id = qisd.impot_mensuel_detail_id " +
            "where qmi.fiche_client_id = ? " +
            "and qmi.statut != 'ARCHIVE' " +
            "and qmi.annee = ? " +
            "and imd.code in ( "+ codesStr + ")";

        BigDecimal montantTotalSousDetail = jdbcTemplate.queryForObject(QUERY2, new Object[]{ ficheClientId, annee}, BigDecimal.class);

        BigDecimal montant1 = montantTotalDetail != null ? montantTotalDetail : BigDecimal.ZERO;
        BigDecimal montant2 = montantTotalSousDetail != null ? montantTotalSousDetail : BigDecimal.ZERO;
        return montant1.add(montant2);
    }
}
