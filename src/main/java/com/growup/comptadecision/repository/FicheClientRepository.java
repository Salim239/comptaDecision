package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.FicheClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the FicheClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheClientRepository extends JpaRepository<FicheClient, Long> {

    Page<FicheClient> findAllByCreatedBy(String creator, Pageable pageable);

    List<FicheClient> findAllByCreatedBy(String creator);

}
