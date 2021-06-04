package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotMensuelLigne;
import com.growup.comptadecision.service.dto.ImpotMensuelLigneDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ImpotMensuel and its DTO ImpotMensuelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImpotMensuelLigneMapper extends EntityMapper<ImpotMensuelLigneDTO, ImpotMensuelLigne> {


    default ImpotMensuelLigne fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotMensuelLigne impotMensuelLigne = new ImpotMensuelLigne();
        impotMensuelLigne.setId(id);
        return impotMensuelLigne;
    }
}
