package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuittanceMensuelleImpotLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleImpotLineRepository extends JpaRepository<QuittanceMensuelleImpotLine, Long> {

    Page<QuittanceMensuelleImpotLine> findAllByCreatedBy(String creator, Pageable pageable);

}
