package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelleLigne;
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
 * Spring Data  repository for the QuittanceMensuelleLigne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleLigneRepository extends JpaRepository<QuittanceMensuelleLigne, Long> {

    Page<QuittanceMensuelleLigne> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("select quittanceMensuelleLigne from QuittanceMensuelleLigne quittanceMensuelleLigne " +
            "left join fetch quittanceMensuelleLigne.quittanceMensuelle quittanceMensuelle " +
            "left join fetch quittanceMensuelle.ficheClient ficheClient " +
            "where ficheClient.id = :ficheClientId " +
            "and quittanceMensuelle.statut in :statuts " +
            "and quittanceMensuelleLigne.quittanceMensuelle.annee = :annee " +
            "and quittanceMensuelleLigne.quittanceMensuelle.mois = :mois " +
            "and quittanceMensuelleLigne.code = :code")
    Optional<QuittanceMensuelleLigne> findByFicheClientIdAndQuittanceStatutsAndAnneeAndMoisAndCode(@Param("ficheClientId") Long ficheClientId,
                                                                                                         @Param("statuts") List<StatutDeclaration> statuts,
                                                                                                         @Param("annee") Integer annee,
                                                                                                         @Param("mois") Integer mois,
                                                                                                         @Param("code") String code);

}
