package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuelClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ImpotMensuelClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotMensuelClientRepository extends JpaRepository<ImpotMensuelClient, Long> {

    List<ImpotMensuelClient> findByFicheClientId(Long ficheClientId);

    Page<ImpotMensuelClient> findAllByCreatedBy(String creator, Pageable pageable);

}
