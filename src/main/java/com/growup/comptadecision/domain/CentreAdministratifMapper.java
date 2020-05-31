package com.growup.comptadecision.domain;

import com.growup.comptadecision.service.dto.CentreAdministratifDTO;
import com.growup.comptadecision.service.dto.VilleDTO;
import com.growup.comptadecision.service.mapper.EntityMapper;
import com.growup.comptadecision.service.mapper.RegionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Ville and its DTO VilleDTO.
 */
@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface CentreAdministratifMapper extends EntityMapper<CentreAdministratifDTO, CentreAdministratif> {

    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.libelle", target = "regionLibelle")
    @Mapping(source = "ville.id", target = "villeId")
    @Mapping(source = "ville.libelle", target = "villeLibelle")
    CentreAdministratifDTO toDto(CentreAdministratif centreAdministratif);

//    @Mapping(source = "regionId", target = "region")
//    @Mapping(source = "villeId", target = "ville")
    CentreAdministratif toEntity(CentreAdministratifDTO centreAdministratifDTO);

    default CentreAdministratif fromId(Long id) {
        if (id == null) {
            return null;
        }
        CentreAdministratif centreAdministratif = new CentreAdministratif();
        centreAdministratif.setId(id);
        return centreAdministratif;
    }
}
