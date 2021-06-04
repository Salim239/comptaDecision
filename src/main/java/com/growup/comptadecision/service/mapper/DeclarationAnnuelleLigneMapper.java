package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.DecalrationAnnuelleLigne;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleLigneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DeclarationAnnuelle and its DTO DeclarationAnnuelleDTO.
 */
@Mapper(componentModel = "spring", uses = {DeclarationAnnuelleMapper.class, ImpotAnnuelMapper.class})
public interface DeclarationAnnuelleLigneMapper extends EntityMapper<DeclarationAnnuelleLigneDTO, DecalrationAnnuelleLigne> {


    @Mapping(source = "impotAnnuel.id", target = "impotAnnuelId")
    @Mapping(source = "declarationAnnuelle.id", target = "declarationAnnuelleId")
    DeclarationAnnuelleLigneDTO toDto(DecalrationAnnuelleLigne decalrationAnnuelleLigne);

    @Mapping(source = "impotAnnuelId", target = "impotAnnuel.id")
    @Mapping(source = "declarationAnnuelleId", target = "declarationAnnuelle.id")
    DecalrationAnnuelleLigne toEntity(DeclarationAnnuelleLigneDTO declarationAnnuelleLigneDTO);

    default DecalrationAnnuelleLigne fromId(Long id) {
        if (id == null) {
            return null;
        }
        DecalrationAnnuelleLigne decalrationAnnuelleLigne = new DecalrationAnnuelleLigne();
        decalrationAnnuelleLigne.setId(id);
        return decalrationAnnuelleLigne;
    }
}
