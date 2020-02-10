package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.DeclarationEmployeurAnnuelle;
import com.growup.comptadecision.domain.FicheClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DeclarationEmployeurAnnuelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclarationEmployeurAnnuelleRepository extends JpaRepository<DeclarationEmployeurAnnuelle, Long> {

    Page<DeclarationEmployeurAnnuelle> findAllByCreatedBy(String creator, Pageable pageable);

}
