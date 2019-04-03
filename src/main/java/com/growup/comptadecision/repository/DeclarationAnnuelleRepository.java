package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.DeclarationAnnuelle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DeclarationAnnuelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclarationAnnuelleRepository extends JpaRepository<DeclarationAnnuelle, Long> {

}
