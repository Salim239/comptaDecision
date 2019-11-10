package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.ImpotMensuel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select impotMensuel from ImpotMensuel impotMensuel " +
            "where impotMensuel.parentImpotMensuel is null ")
    List<ImpotMensuel> findWithoutChildren();

    @Query("select impotMensuel from ImpotMensuel impotMensuel " +
            "left join fetch impotMensuel.impotMensuelDetails impotMensuelDetail " +
            "where impotMensuel.parentImpotMensuel.id in :parentIds ")
    List<ImpotMensuel> findByParentIds(@Param("parentIds") List<Long> parentIds);

    @Query("select impotMensuel from ImpotMensuel impotMensuel " +
            "left join fetch impotMensuel.impotMensuelDetails impotMensuelDetail " +
            "where impotMensuel.parentImpotMensuel.id = :parentId")
    List<ImpotMensuel> findByParentId(@Param("parentId") Long parentId);

    @Query("select distinct impotMensuel from ImpotMensuel impotMensuel " +
            "left join fetch impotMensuel.impotMensuelDetails impotMensuelDetail " +
            "where impotMensuel.id in :ids " +
            "or impotMensuel.parentImpotMensuel.id in :ids")
    List<ImpotMensuel> findAndChildByIds(@Param("ids") List<Long> ids);

    @Query("select distinct impotMensuel from ImpotMensuel impotMensuel " +
            "left join fetch impotMensuel.impotMensuelClients impotMensuelClient " +
            "left join impotMensuel.impotMensuelDetails impotMensuelDetail " +
            "where impotMensuelClient.ficheClient.id =:ficheClientId " +
            "and impotMensuelClient.applicable = true ")
    List<ImpotMensuel> findImpotMensuelApplicableByFicheClientId(@Param("ficheClientId") Long ficheClientId);

}
