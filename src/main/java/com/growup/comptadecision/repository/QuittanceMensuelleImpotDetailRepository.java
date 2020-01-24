package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the QuittanceMensuelleImpotDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleImpotDetailRepository extends JpaRepository<QuittanceMensuelleImpotDetail, Long> {

    Page<QuittanceMensuelleImpotDetail> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("select quittanceMensuelleImpotDetail from QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail " +
            "left join fetch quittanceMensuelleImpotDetail.quittanceMensuelleImpot quittanceMensuelleImpot " +
            "left join fetch quittanceMensuelleImpot.ficheClient ficheClient " +
            "where ficheClient.id = :ficheClientId " +
            "and quittanceMensuelleImpot.statut in :statuts " +
            "and quittanceMensuelleImpotDetail.quittanceMensuelleImpot.annee = :annee " +
            "and quittanceMensuelleImpotDetail.quittanceMensuelleImpot.mois = :mois " +
            "and quittanceMensuelleImpotDetail.code = :code")
    Optional<QuittanceMensuelleImpotDetail> findByFicheClientIdAndQuittanceStatutsAndAnneeAndMoisAndCode(@Param("ficheClientId") Long ficheClientId,
                                                                                                         @Param("statuts") List<StatutDeclaration> statuts,
                                                                                                         @Param("annee") Integer annee,
                                                                                                         @Param("mois") Integer mois,
                                                                                                         @Param("code") String code);

}
