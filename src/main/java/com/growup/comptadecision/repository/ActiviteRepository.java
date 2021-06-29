package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.Activite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Activite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActiviteRepository extends JpaSpecificationExecutor<Activite>, JpaRepository<Activite, Long> {

    Page<Activite> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("SELECT activite FROM Activite  activite WHERE activite.secteurActivite.id = :secteurActiviteId")
    List<Activite> findBySecteurActiviteId(@Param("secteurActiviteId") Long secteurActiviteId);

}
