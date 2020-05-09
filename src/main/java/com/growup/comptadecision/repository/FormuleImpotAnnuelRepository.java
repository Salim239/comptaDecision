package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.FormuleImpotAnnuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImpotMensuel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormuleImpotAnnuelRepository extends JpaRepository<FormuleImpotAnnuel, Long> {

}
