package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.SecteurActiviteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SecteurActivite and its DTO SecteurActiviteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SecteurActiviteMapper extends EntityMapper<SecteurActiviteDTO, SecteurActivite> {



    default SecteurActivite fromId(Long id) {
        if (id == null) {
            return null;
        }
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setId(id);
        return secteurActivite;
    }
}
