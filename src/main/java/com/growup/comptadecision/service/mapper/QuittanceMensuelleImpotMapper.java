package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuittanceMensuelleImpot and its DTO QuittanceMensuelleImpotDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class})
public interface QuittanceMensuelleImpotMapper extends EntityMapper<QuittanceMensuelleImpotDTO, QuittanceMensuelleImpot> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    QuittanceMensuelleImpotDTO toDto(QuittanceMensuelleImpot quittanceMensuelleImpot);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    QuittanceMensuelleImpot toEntity(QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO);

    default QuittanceMensuelleImpot fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelleImpot quittanceMensuelleImpot = new QuittanceMensuelleImpot();
        quittanceMensuelleImpot.setId(id);
        return quittanceMensuelleImpot;
    }
}
