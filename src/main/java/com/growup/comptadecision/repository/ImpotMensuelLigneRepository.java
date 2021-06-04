package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuelLigne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ImpotMensuelLigne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotMensuelLigneRepository extends JpaRepository<ImpotMensuelLigne, Long> {

    Page<ImpotMensuelLigne> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("SELECT impotMensuelLigne FROM ImpotMensuelLigne impotMensuelLigne " +
            "WHERE impotMensuelLigne.id in :impotMensuelLigneIds order by impotMensuelLigne.impotMensuel.id")

    List<ImpotMensuelLigne> findByImpotMensuelIds(@Param("impotMensuelLigneIds")List<Long> impotMensuelLigneIds);

}
