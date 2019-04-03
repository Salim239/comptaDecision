package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.CnssDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cnss and its DTO CnssDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class})
public interface CnssMapper extends EntityMapper<CnssDTO, Cnss> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    CnssDTO toDto(Cnss cnss);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    Cnss toEntity(CnssDTO cnssDTO);

    default Cnss fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cnss cnss = new Cnss();
        cnss.setId(id);
        return cnss;
    }
}
