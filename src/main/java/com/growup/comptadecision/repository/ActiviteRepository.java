package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.Activite;
import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.service.dto.ActiviteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Activite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    Page<Activite> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("SELECT activite FROM Activite  activite WHERE activite.secteurActivite.id = :secteurActiviteId")
    List<Activite> findBySecteurActiviteId(@Param("secteurActiviteId") Long secteurActiviteId);

}
