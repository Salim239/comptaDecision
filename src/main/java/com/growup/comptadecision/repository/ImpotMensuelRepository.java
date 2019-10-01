package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ImpotMensuel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpotMensuelRepository extends JpaRepository<ImpotMensuel, Long> {

    Page<ImpotMensuel> findAllByCreatedBy(String creator, Pageable pageable);

    @Query("select impotMensuel from ImpotMensuel impotMensuel " +
            "where impotMensuel.parent = true")
    List<ImpotMensuel> findParents();

//    @Query("select impotMensuel from ImpotMensuel impotMensuel " +
//            "where impotMensuel.parent = true " +
//            "and impotMensuel.id not in (select subImpotMensuel.parentImpotMensuel.id from ImpotMensuel subImpotMensuel)")
//    List<ImpotMensuel> findParentsWithoutChildren();

}
