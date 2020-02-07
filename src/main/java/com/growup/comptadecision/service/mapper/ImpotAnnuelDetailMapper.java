package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotAnnuelDetail;
import com.growup.comptadecision.service.dto.ImpotAnnuelDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ImpotAnnuel and its DTO ImpotAnnuelDTO.
 */
@Mapper(componentModel = "spring", uses = {ImpotMensuelMapper.class, ImpotMensuelDetailMapper.class})
public interface ImpotAnnuelDetailMapper extends EntityMapper<ImpotAnnuelDetailDTO, ImpotAnnuelDetail> {


    default ImpotAnnuelDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotAnnuelDetail impotAnnuelDetail = new ImpotAnnuelDetail();
        impotAnnuelDetail.setId(id);
        return impotAnnuelDetail;
    }
}
