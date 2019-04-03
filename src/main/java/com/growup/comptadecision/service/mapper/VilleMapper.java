package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.VilleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ville and its DTO VilleDTO.
 */
@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface VilleMapper extends EntityMapper<VilleDTO, Ville> {

    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.libelle", target = "regionLibelle")
    VilleDTO toDto(Ville ville);

    @Mapping(source = "regionId", target = "region")
    Ville toEntity(VilleDTO villeDTO);

    default Ville fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ville ville = new Ville();
        ville.setId(id);
        return ville;
    }
}
