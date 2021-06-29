package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.CabinetComptable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CabinetComptable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CabinetComptableRepository extends JpaSpecificationExecutor<CabinetComptable>, JpaRepository<CabinetComptable, Long> {

}
