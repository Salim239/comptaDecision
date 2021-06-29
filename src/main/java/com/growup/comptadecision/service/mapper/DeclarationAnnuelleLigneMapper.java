package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.DeclarationAnnuelleLigne;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleLigneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DeclarationAnnuelle and its DTO DeclarationAnnuelleDTO.
 */
@Mapper(componentModel = "spring", uses = {DeclarationAnnuelleMapper.class, ImpotAnnuelMapper.class})
public interface DeclarationAnnuelleLigneMapper extends EntityMapper<DeclarationAnnuelleLigneDTO, DeclarationAnnuelleLigne> {


    @Mapping(source = "impotAnnuel.id", target = "impotAnnuelId")
    @Mapping(source = "declarationAnnuelle.id", target = "declarationAnnuelleId")
    DeclarationAnnuelleLigneDTO toDto(DeclarationAnnuelleLigne declarationAnnuelleLigne);

    @Mapping(source = "impotAnnuelId", target = "impotAnnuel.id")
    @Mapping(source = "declarationAnnuelleId", target = "declarationAnnuelle.id")
    DeclarationAnnuelleLigne toEntity(DeclarationAnnuelleLigneDTO declarationAnnuelleLigneDTO);

    default DeclarationAnnuelleLigne fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeclarationAnnuelleLigne declarationAnnuelleLigne = new DeclarationAnnuelleLigne();
        declarationAnnuelleLigne.setId(id);
        return declarationAnnuelleLigne;
    }
}
