package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ApplicationUserFicheClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApplicationUserFicheClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationUserFicheClientRepository extends JpaSpecificationExecutor<ApplicationUserFicheClient>, JpaRepository<ApplicationUserFicheClient, Long> {

}
