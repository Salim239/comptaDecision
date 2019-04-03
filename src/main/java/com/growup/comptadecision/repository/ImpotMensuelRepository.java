package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImpotMensuel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotMensuelRepository extends JpaRepository<ImpotMensuel, Long> {

}
