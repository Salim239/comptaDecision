package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuittanceMensuelleImpotLine and its DTO QuittanceMensuelleImpotLineDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleImpotMapper.class})
public interface QuittanceMensuelleImpotLineMapper extends EntityMapper<QuittanceMensuelleImpotLineDTO, QuittanceMensuelleImpotLine> {

    @Mapping(source = "quittanceMensuelleImpot.id", target = "quittanceMensuelleImpotId")
    QuittanceMensuelleImpotLineDTO toDto(QuittanceMensuelleImpotLine quittanceMensuelleImpotLine);

    @Mapping(target = "impotMensuelClients", ignore = true)
    @Mapping(source = "quittanceMensuelleImpotId", target = "quittanceMensuelleImpot")
    QuittanceMensuelleImpotLine toEntity(QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO);

    default QuittanceMensuelleImpotLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelleImpotLine quittanceMensuelleImpotLine = new QuittanceMensuelleImpotLine();
        quittanceMensuelleImpotLine.setId(id);
        return quittanceMensuelleImpotLine;
    }
}
