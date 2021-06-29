package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.CategorieCnssGerant;
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
public interface CategorieCnssGerantRepository extends JpaSpecificationExecutor<CategorieCnssGerant>, JpaRepository<CategorieCnssGerant, Long> {

    Page<CategorieCnssGerant> findAllByCreatedBy(String creator, Pageable pageable);

}
