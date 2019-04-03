package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.AcompteProvisionnel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcompteProvisionnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcompteProvisionnelRepository extends JpaRepository<AcompteProvisionnel, Long> {

}
