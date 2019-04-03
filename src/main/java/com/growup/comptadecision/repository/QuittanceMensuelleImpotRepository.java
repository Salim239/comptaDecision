package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuittanceMensuelleImpot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleImpotRepository extends JpaRepository<QuittanceMensuelleImpot, Long> {

}
