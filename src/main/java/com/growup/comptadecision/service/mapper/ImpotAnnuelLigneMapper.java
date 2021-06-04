package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotAnnuelLigne;
import com.growup.comptadecision.domain.ImpotMensuelLigne;
import com.growup.comptadecision.service.dto.ImpotAnnuelLigneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ImpotAnnuel and its DTO ImpotAnnuelDTO.
 */
@Mapper(componentModel = "spring", uses = {ImpotMensuelMapper.class, ImpotMensuelLigneMapper.class})
public interface ImpotAnnuelLigneMapper extends EntityMapper<ImpotAnnuelLigneDTO, ImpotAnnuelLigne> {

    default ImpotMensuelLigne getImpotMensuelLigne(ImpotAnnuelLigneDTO impotAnnuelLigneDTO) {
        ImpotMensuelLigne impotMensuelLigne = new ImpotMensuelLigne();
        impotMensuelLigne.setId(impotAnnuelLigneDTO.getImpotMensuelLigneId());
        return impotMensuelLigne;
    }

    default ImpotAnnuelLigne fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotAnnuelLigne impotAnnuelLigne = new ImpotAnnuelLigne();
        impotAnnuelLigne.setId(id);
        return impotAnnuelLigne;
    }

    @Mapping(target = "impotMensuelLigne", expression = "java(this.getImpotMensuelLigne(impotAnnuelLigneDTO))")
    ImpotAnnuelLigne toEntity(ImpotAnnuelLigneDTO impotAnnuelLigneDTO);

    @Mapping(source = "impotMensuelLigne.id", target = "impotMensuelLigneId")
    @Mapping(source = "impotMensuelLigne.libelle", target = "impotMensuelLigneLibelle")
    @Mapping(source = "impotMensuelLigne.impotMensuel.id", target = "impotMensuelId")
    @Mapping(source = "impotMensuelLigne.impotMensuel.libelle", target = "impotMensuelLibelle")
    ImpotAnnuelLigneDTO toDto(ImpotAnnuelLigne impotAnnuelLigne);
}
