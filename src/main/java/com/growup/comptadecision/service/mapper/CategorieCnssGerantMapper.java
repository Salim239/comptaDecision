package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.CategorieCnssGerant;
import com.growup.comptadecision.service.dto.CategorieCnssGerantDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity CategorieCnssGerant and its DTO CategorieCnssGerantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieCnssGerantMapper extends EntityMapper<CategorieCnssGerantDTO, CategorieCnssGerant> {



    default CategorieCnssGerant fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieCnssGerant categorieCnssGerant = new CategorieCnssGerant();
        categorieCnssGerant.setId(id);
        return categorieCnssGerant;
    }
}
