package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Region entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegionRepository extends JpaSpecificationExecutor<Region>, JpaRepository<Region, Long> {

    Page<Region> findAllByCreatedBy(String creator, Pageable pageable);

}
