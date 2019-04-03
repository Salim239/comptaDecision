package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.ActiviteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Activite and its DTO ActiviteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActiviteMapper extends EntityMapper<ActiviteDTO, Activite> {



    default Activite fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activite activite = new Activite();
        activite.setId(id);
        return activite;
    }
}
