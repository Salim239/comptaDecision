package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotAnnuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImpotMensuel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotAnnuelRepository extends JpaRepository<ImpotAnnuel, Long> {

}
