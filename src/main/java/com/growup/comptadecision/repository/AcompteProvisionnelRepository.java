package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.AcompteProvisionnel;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the AcompteProvisionnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcompteProvisionnelRepository extends JpaRepository<AcompteProvisionnel, Long> {

    Page<AcompteProvisionnel> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("select acompte from AcompteProvisionnel acompte " +
            "where acompte.ficheClient.id = :ficheClientId " +
            "and acompte.annee = :annee " +
            "and acompte.numero = :numero " +
            "and acompte.statut <> 'ARCHIVE'")
    Optional<AcompteProvisionnel> findValidByFicheClientIdAndAnneeAndNumero(@Param("ficheClientId") Long ficheClientId, @Param("annee") Integer annee, @Param("numero") Integer numero);

    @Query("select acompte from AcompteProvisionnel acompte " +
        "where acompte.ficheClient.id = :ficheClientId " +
        "and acompte.annee = :annee " +
        "and acompte.numero = :numero")
    List<AcompteProvisionnel> findByFicheClientIdAndAnneeAndNumero(@Param("ficheClientId") Long ficheClientId, @Param("annee") Integer annee, @Param("numero") Integer numero);

    @Query("SELECT SUM(ap.montantAcompteProvisionnel) FROM AcompteProvisionnel  ap WHERE ap.annee = :annee and ap.ficheClient.id = :ficheClientId and ap.montantAcompteProvisionnel > 0")
    BigDecimal sumpAcomptePrevisionnelPositifs(@Param("ficheClientId") Long ficheClientId, @Param("annee") Integer annee);

    @Query("select acompte from AcompteProvisionnel acompte " +
        "where acompte.ficheClient.id = :ficheClientId " +
        "and acompte.annee = :annee " +
        "and acompte.numero = :numero " +
        "and acompte.statut <> 'ARCHIVED'")
    Optional<AcompteProvisionnel> findByFicheClientIdAndAnneeAndNumeroAndNotArchived(@Param("ficheClientId") Long ficheClientId, @Param("annee") Integer annee, @Param("numero") Integer numero);

    @Query("select acompte from AcompteProvisionnel acompte " +
        "where acompte.ficheClient.id = :ficheClientId " +
        "and acompte.annee = :annee " +
        "and acompte.numero = :numero " +
        "and acompte.type = :type")
    Optional<AcompteProvisionnel> findByFicheClientIdAndAnneeAndNumeroAndType(@Param("ficheClientId") Long ficheClientId, @Param("annee") Integer annee,
                                                                              @Param("numero") Integer numero, @Param("type") TypeDeclaration type);

}
