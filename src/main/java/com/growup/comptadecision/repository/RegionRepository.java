package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Region entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Page<Region> findAllByCreatedBy(String creator, Pageable pageable);

}
