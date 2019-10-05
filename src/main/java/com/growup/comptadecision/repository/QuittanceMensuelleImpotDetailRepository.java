package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuittanceMensuelleImpotDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleImpotDetailRepository extends JpaRepository<QuittanceMensuelleImpotDetail, Long> {

    Page<QuittanceMensuelleImpotDetail> findAllByCreatedBy(String creator, Pageable pageable);

}
