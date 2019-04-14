package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.Activite;
import com.growup.comptadecision.domain.FicheClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Activite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    Page<Activite> findAllByCreatedBy(String creator, Pageable pageable);

}
