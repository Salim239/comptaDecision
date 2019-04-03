package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotLine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuittanceMensuelleImpotLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleImpotLineRepository extends JpaRepository<QuittanceMensuelleImpotLine, Long> {

}
