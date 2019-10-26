package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuittanceMensuelleImpot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleImpotRepository extends JpaRepository<QuittanceMensuelleImpot, Long> {

    Page<QuittanceMensuelleImpot> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("SELECT quittanceMensuelleImpot FROM QuittanceMensuelleImpot quittanceMensuelleImpot " +
//            "LEFT JOIN FETCH quittanceMensuelleImpot.ficheClient " +
            "WHERE quittanceMensuelleImpot.annee = :annee " +
            "and quittanceMensuelleImpot.mois = :mois " +
            "and quittanceMensuelleImpot.ficheClient.id = :ficheClientId " +
            "and quittanceMensuelleImpot.typeDeclaration = :typeDeclaration")
    QuittanceMensuelleImpot findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(@Param("annee") Integer annee, @Param("mois") Integer mois,
                                                                                 @Param("ficheClientId") Long ficheClientId, @Param("typeDeclaration") TypeDeclaration typeDeclaration);

}
