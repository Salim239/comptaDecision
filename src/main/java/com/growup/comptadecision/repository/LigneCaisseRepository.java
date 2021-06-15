package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.LigneCaisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LigneCaisse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneCaisseRepository extends JpaRepository<LigneCaisse, Long> {

}
