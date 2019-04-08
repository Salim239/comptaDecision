package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuelClient;
import com.growup.comptadecision.service.dto.ImpotMensuelClientDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the ImpotMensuelClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotMensuelClientRepository extends JpaRepository<ImpotMensuelClient, Long> {

    List<ImpotMensuelClient> findByFicheClientIdAndMois(Long ficheClientId, Integer mois);

}
