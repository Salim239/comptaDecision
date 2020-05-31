package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.ActiviteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Activite and its DTO ActiviteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActiviteMapper extends EntityMapper<ActiviteDTO, Activite> {


    @Override
    @Mapping(target = "secteurActivite", expression = "java(new com.growup.comptadecision.domain.SecteurActivite(dto.getSecteurActiviteId()))")
    Activite toEntity(ActiviteDTO dto);

    @Mapping(target = "secteurActiviteId", source = "secteurActivite.id")
    @Mapping(target = "secteurActiviteLibelle", source="secteurActivite.libelle")
    ActiviteDTO toDto(Activite entity);



    default Activite fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activite activite = new Activite();
        activite.setId(id);
        return activite;
    }
}
