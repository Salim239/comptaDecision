package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.Cnss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Cnss entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CnssRepository extends JpaRepository<Cnss, Long> {

    Page<Cnss> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("select cnss from Cnss cnss where cnss.ficheClient.id = :ficheClientId " +
            "and cnss.annee = :annee " +
            "and cnss.trimestre = :trimestre")
    List<Cnss> findByFicheClientIdAndAnneeAndTrimestre(@Param("ficheClientId") Long ficheClientId, @Param("annee") Integer annee, @Param("trimestre") Integer trimestre);

}
