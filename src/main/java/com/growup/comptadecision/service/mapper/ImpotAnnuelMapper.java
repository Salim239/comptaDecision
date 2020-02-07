package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotAnnuel;
import com.growup.comptadecision.service.dto.ImpotAnnuelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ImpotAnnuel and its DTO ImpotAnnuelDTO.
 */
@Mapper(componentModel = "spring", uses = {ImpotAnnuelDetailMapper.class})
public interface ImpotAnnuelMapper extends EntityMapper<ImpotAnnuelDTO, ImpotAnnuel> {

    @Mapping(target = "impotAnnuelDetails", expression = "java(" +
            "impotAnnuelDTO.getImpotAnnuelDetails().stream().map(impotAnnuelDetailDto ->  {" +
            "com.growup.comptadecision.domain.ImpotAnnuelDetail impotAnnuelDetail = impotAnnuelDetailMapper.toEntity(impotAnnuelDetailDto);" +
            "impotAnnuelDetail.setImpotAnnuel(impotAnnuel);" +
            "return impotAnnuelDetail;})" +
            ".collect(java.util.stream.Collectors.toList()))")
    ImpotAnnuel toEntity(ImpotAnnuelDTO impotAnnuelDTO);

    ImpotAnnuelDTO toDto(ImpotAnnuel impotAnnuel);

    default ImpotAnnuel fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotAnnuel impotAnnuel = new ImpotAnnuel();
        impotAnnuel.setId(id);
        return impotAnnuel;
    }
}
