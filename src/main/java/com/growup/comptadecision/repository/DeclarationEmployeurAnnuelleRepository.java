package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.DeclarationEmployeurAnnuelle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the DeclarationEmployeurAnnuelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclarationEmployeurAnnuelleRepository extends JpaSpecificationExecutor<DeclarationEmployeurAnnuelle>, JpaRepository<DeclarationEmployeurAnnuelle, Long> {

    Page<DeclarationEmployeurAnnuelle> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("select declaration from DeclarationEmployeurAnnuelle  declaration " +
            "where declaration.ficheClient.id = :ficheClientId " +
            "and declaration.annee = :annee")
    Optional<DeclarationEmployeurAnnuelle> findByFicheClientIdAndAnnee(@Param("ficheClientId") Long ficheClientId, @Param("annee") Integer annee);

}
