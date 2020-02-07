package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotSousDetail;
import com.growup.comptadecision.repository.custom.QuittanceMensuelleImpotSousDetailRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuittanceMensuelleImpotSousDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuittanceMensuelleImpotSousDetailRepository extends JpaRepository<QuittanceMensuelleImpotSousDetail, Long>, QuittanceMensuelleImpotSousDetailRepositoryCustom {

    Page<QuittanceMensuelleImpotSousDetail> findAllByCreatedBy(String creator, Pageable pageable);

}
