package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotAnnuelLigne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImpotMensuel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotAnnuelLigneRepository extends JpaSpecificationExecutor<ImpotAnnuelLigne>, JpaRepository<ImpotAnnuelLigne, Long> {

}
