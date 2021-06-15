package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.LigneCaisse;
import com.growup.comptadecision.service.dto.LigneCaisseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link LigneCaisse} and its DTO {@link LigneCaisseDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleMapper.class, DeclarationAnnuelleMapper.class, CnssMapper.class, CaisseMapper.class})
public interface LigneCaisseMapper extends EntityMapper<LigneCaisseDTO, LigneCaisse> {

    @Mapping(source = "quittanceMensuelle.id", target = "quittanceMensuelleId")
    @Mapping(source = "declarationAnnuelle.id", target = "declarationAnnuelleId")
    @Mapping(source = "cnss.id", target = "cnssId")
    @Mapping(source = "caisse.id", target = "caisseId")
    LigneCaisseDTO toDto(LigneCaisse ligneCaisse);

    @Mapping(source = "quittanceMensuelleId", target = "quittanceMensuelle")
    @Mapping(source = "declarationAnnuelleId", target = "declarationAnnuelle")
    @Mapping(source = "cnssId", target = "cnss")
    @Mapping(source = "caisseId", target = "caisse")
    LigneCaisse toEntity(LigneCaisseDTO ligneCaisseDTO);

    default LigneCaisse fromId(Long id) {
        if (id == null) {
            return null;
        }
        LigneCaisse ligneCaisse = new LigneCaisse();
        ligneCaisse.setId(id);
        return ligneCaisse;
    }
}
