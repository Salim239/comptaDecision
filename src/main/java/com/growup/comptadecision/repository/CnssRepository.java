package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.Cnss;
import com.growup.comptadecision.domain.FicheClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Cnss entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CnssRepository extends JpaRepository<Cnss, Long> {

    Page<Cnss> findAllByCreatedBy(String creator, Pageable pageable);

}
