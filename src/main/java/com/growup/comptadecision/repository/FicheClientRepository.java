package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.FicheClient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FicheClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheClientRepository extends JpaRepository<FicheClient, Long> {

}
