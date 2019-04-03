package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.ImpotMensuelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ImpotMensuel and its DTO ImpotMensuelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImpotMensuelMapper extends EntityMapper<ImpotMensuelDTO, ImpotMensuel> {



    default ImpotMensuel fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotMensuel impotMensuel = new ImpotMensuel();
        impotMensuel.setId(id);
        return impotMensuel;
    }
}
