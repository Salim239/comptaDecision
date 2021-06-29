package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.SecteurActivite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SecteurActivite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecteurActiviteRepository extends JpaSpecificationExecutor<SecteurActivite>, JpaRepository<SecteurActivite, Long> {

    Page<SecteurActivite> findAllByCreatedBy(String creator, Pageable pageable);


}
