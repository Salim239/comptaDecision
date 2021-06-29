package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelle;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the QuittanceMensuelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleRepository extends JpaSpecificationExecutor<QuittanceMensuelle>, JpaRepository<QuittanceMensuelle, Long> {

    Page<QuittanceMensuelle> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("SELECT quittanceMensuelle FROM QuittanceMensuelle quittanceMensuelle " +
//            "LEFT JOIN FETCH quittanceMensuelle.ficheClient " +
            "WHERE quittanceMensuelle.annee = :annee " +
            "and quittanceMensuelle.mois = :mois " +
            "and quittanceMensuelle.ficheClient.id = :ficheClientId " +
            "and quittanceMensuelle.typeDeclaration = :typeDeclaration")
    Optional<QuittanceMensuelle> findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(@Param("annee") Integer annee, @Param("mois") Integer mois,
                                                                                      @Param("ficheClientId") Long ficheClientId, @Param("typeDeclaration") TypeDeclaration typeDeclaration);

    @Query("SELECT quittanceMensuelle FROM QuittanceMensuelle quittanceMensuelle " +
            "WHERE quittanceMensuelle.annee = :annee " +
            "and quittanceMensuelle.mois = :mois " +
            "and quittanceMensuelle.ficheClient.id = :ficheClientId ")
    List<QuittanceMensuelle> findByAnneeAndMoisAndFicheClientId(@Param("annee") Integer annee, @Param("mois") Integer mois,
                                                                @Param("ficheClientId") Long ficheClientId);

}
