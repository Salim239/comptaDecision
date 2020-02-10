package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.AcompteProvisionnel;
import com.growup.comptadecision.domain.FicheClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcompteProvisionnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcompteProvisionnelRepository extends JpaRepository<AcompteProvisionnel, Long> {

    Page<AcompteProvisionnel> findAllByCreatedBy(String creator, Pageable pageable);

}
