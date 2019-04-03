package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuelClient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImpotMensuelClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotMensuelClientRepository extends JpaRepository<ImpotMensuelClient, Long> {

}
