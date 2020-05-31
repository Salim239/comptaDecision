package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.CentreAdministratif;
import com.growup.comptadecision.domain.Ville;
import com.growup.comptadecision.domain.enumeration.TypeCentreAdministratif;
import com.growup.comptadecision.service.dto.CentreAdministratifDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Ville entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CentreAdministratifRepository extends JpaRepository<CentreAdministratif, Long> {

    Page<CentreAdministratif> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("SELECT centreAdministratif FROM CentreAdministratif centreAdministratif WHERE centreAdministratif.type = :typeCentreAdministratif")
    List<CentreAdministratif> findByType(@Param("typeCentreAdministratif") TypeCentreAdministratif typeCentreAdministratif);

}
