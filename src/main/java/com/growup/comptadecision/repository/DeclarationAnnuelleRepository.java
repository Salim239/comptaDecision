package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.DeclarationAnnuelle;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the DeclarationAnnuelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclarationAnnuelleRepository extends JpaRepository<DeclarationAnnuelle, Long> {

    Page<DeclarationAnnuelle> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("select declarationAnnuelle from DeclarationAnnuelle declarationAnnuelle " +
            "left join fetch declarationAnnuelle.ficheClient ficheClient " +
            "where declarationAnnuelle.annee = :annee " +
            "and ficheClient.id = :ficheClientId")
    List<DeclarationAnnuelle> findByAnneeAndFicheClientId(@Param("annee") Integer annee, @Param("ficheClientId") Long ficheClientId);

    @Query("select declarationAnnuelle from DeclarationAnnuelle declarationAnnuelle " +
        "left join fetch declarationAnnuelle.ficheClient ficheClient " +
        "where declarationAnnuelle.annee = :annee " +
        "and declarationAnnuelle.statut <> 'ARCHIVE' " +
        "and ficheClient.id = :ficheClientId ")
    Optional<DeclarationAnnuelle> findValidByAnneeAndFicheClientId(@Param("annee") Integer annee, @Param("ficheClientId") Long ficheClientId);

    @Query("SELECT declarationAnnuelle FROM  DeclarationAnnuelle declarationAnnuelle " +
//            "LEFT JOIN FETCH quittanceMensuelleImpot.ficheClient " +
            "WHERE declarationAnnuelle.annee = :annee " +
            "and declarationAnnuelle.ficheClient.id = :ficheClientId " +
            "and declarationAnnuelle.typeDeclaration = :typeDeclaration")
    Optional<DeclarationAnnuelle> findByAnneeAndFicheClientIdAndTypeDeclaration(@Param("annee") Integer annee,
                                                                                @Param("ficheClientId") Long ficheClientId,
                                                                                @Param("typeDeclaration") TypeDeclaration typeDeclaration );


}
