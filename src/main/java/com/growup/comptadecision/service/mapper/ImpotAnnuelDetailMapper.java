package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotAnnuelDetail;
import com.growup.comptadecision.domain.ImpotMensuelDetail;
import com.growup.comptadecision.service.dto.ImpotAnnuelDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ImpotAnnuel and its DTO ImpotAnnuelDTO.
 */
@Mapper(componentModel = "spring", uses = {ImpotMensuelMapper.class, ImpotMensuelDetailMapper.class})
public interface ImpotAnnuelDetailMapper extends EntityMapper<ImpotAnnuelDetailDTO, ImpotAnnuelDetail> {

    default ImpotMensuelDetail getImpotMensuelDetail(ImpotAnnuelDetailDTO impotAnnuelDetailDTO) {
        ImpotMensuelDetail impotMensuelDetail = new ImpotMensuelDetail();
        impotMensuelDetail.setId(impotAnnuelDetailDTO.getImpotMensuelDetailId());
        return impotMensuelDetail;
    }

    default ImpotAnnuelDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotAnnuelDetail impotAnnuelDetail = new ImpotAnnuelDetail();
        impotAnnuelDetail.setId(id);
        return impotAnnuelDetail;
    }

    @Mapping(target = "impotMensuelDetail", expression = "java(this.getImpotMensuelDetail(impotAnnuelDetailDTO))")
    ImpotAnnuelDetail toEntity(ImpotAnnuelDetailDTO impotAnnuelDetailDTO);

    @Mapping(source = "impotMensuelDetail.id", target = "impotMensuelDetailId")
    @Mapping(source = "impotMensuelDetail.libelle", target = "impotMensuelDetailLibelle")
    @Mapping(source = "impotMensuelDetail.impotMensuel.id", target = "impotMensuelId")
    @Mapping(source = "impotMensuelDetail.impotMensuel.libelle", target = "impotMensuelLibelle")
    ImpotAnnuelDetailDTO toDto(ImpotAnnuelDetail impotAnnuelDetail);
}
