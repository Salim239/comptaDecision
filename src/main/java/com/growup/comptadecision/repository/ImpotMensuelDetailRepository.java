package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuelDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ImpotMensuelDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotMensuelDetailRepository extends JpaRepository<ImpotMensuelDetail, Long> {

    Page<ImpotMensuelDetail> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("SELECT impotMensuelDetail FROM ImpotMensuelDetail impotMensuelDetail " +
            "WHERE impotMensuelDetail.id in :impotMensuelDetailIds order by impotMensuelDetail.impotMensuel.id")

    List<ImpotMensuelDetail> findByImpotMensuelIds(@Param("impotMensuelDetailIds")List<Long> impotMensuelDetailIds);

}
