package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotAnnuel;
import com.growup.comptadecision.service.dto.ImpotAnnuelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ImpotAnnuel and its DTO ImpotAnnuelDTO.
 */
@Mapper(componentModel = "spring", uses = {ImpotAnnuelLigneMapper.class})
public interface ImpotAnnuelMapper extends EntityMapper<ImpotAnnuelDTO, ImpotAnnuel> {

    @Mapping(target = "impotAnnuelLignes", expression = "java(" +
            "impotAnnuelDTO.getImpotAnnuelLignes().stream().map(impotAnnuelLigneDto ->  {" +
            "com.growup.comptadecision.domain.ImpotAnnuelLigne impotAnnuelLigne = impotAnnuelLigneMapper.toEntity(impotAnnuelLigneDto);" +
            "impotAnnuelLigne.setImpotAnnuel(impotAnnuel);" +
            "return impotAnnuelLigne;})" +
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
