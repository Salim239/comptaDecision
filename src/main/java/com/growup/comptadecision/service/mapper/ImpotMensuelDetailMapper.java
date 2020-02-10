package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotMensuelDetail;
import com.growup.comptadecision.service.dto.ImpotMensuelDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ImpotMensuel and its DTO ImpotMensuelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImpotMensuelDetailMapper extends EntityMapper<ImpotMensuelDetailDTO, ImpotMensuelDetail> {


    default ImpotMensuelDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotMensuelDetail impotMensuelDetail = new ImpotMensuelDetail();
        impotMensuelDetail.setId(id);
        return impotMensuelDetail;
    }
}
