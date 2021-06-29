package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Caisse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaisseRepository extends JpaSpecificationExecutor<Caisse>, JpaRepository<Caisse, Long> {

    @Query("SELECT caisse FROM Caisse caisse WHERE caisse.ficheClient.id = :ficheClientId and caisse.cloturee <> true")
    Optional<Caisse> findByClientId(@Param("ficheClientId") Long ficheClientId);

}
