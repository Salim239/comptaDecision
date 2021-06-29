package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelleSousLigne;
import com.growup.comptadecision.repository.custom.QuittanceMensuelleSousLigneRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuittanceMensuelleSousLigne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleSousLigneRepository extends JpaSpecificationExecutor<QuittanceMensuelleSousLigne>, JpaRepository<QuittanceMensuelleSousLigne, Long>, QuittanceMensuelleSousLigneRepositoryCustom {

    Page<QuittanceMensuelleSousLigne> findAllByCreatedBy(String creator, Pageable pageable);

}
