package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuittanceMensuelleImpot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleImpotRepository extends JpaRepository<QuittanceMensuelleImpot, Long> {

    Page<QuittanceMensuelleImpot> findAllByCreatedBy(String creator, Pageable pageable);

}
