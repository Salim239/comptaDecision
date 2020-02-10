package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ville entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {

    Page<Ville> findAllByCreatedBy(String creator, Pageable pageable);

}
