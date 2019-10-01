package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuelDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImpotMensuelDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotMensuelDetailRepository extends JpaRepository<ImpotMensuelDetail, Long> {

    Page<ImpotMensuelDetail> findAllByCreatedBy(String creator, Pageable pageable);

}
