package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.DeclarationAnnuelleLigne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DeclarationAnnuelle ligne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclarationAnnuelleLigneRepository extends JpaSpecificationExecutor<DeclarationAnnuelleLigne>, JpaRepository<DeclarationAnnuelleLigne, Long> {


}
