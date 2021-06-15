package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.Caisse;
import com.growup.comptadecision.service.dto.CaisseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Caisse} and its DTO {@link CaisseDTO}.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class})
public interface CaisseMapper extends EntityMapper<CaisseDTO, Caisse> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    CaisseDTO toDto(Caisse caisse);

    @Mapping(target = "ligneCaisses", ignore = true)
    @Mapping(source = "ficheClientId", target = "ficheClient")
    Caisse toEntity(CaisseDTO caisseDTO);

    default Caisse fromId(Long id) {
        if (id == null) {
            return null;
        }
        Caisse caisse = new Caisse();
        caisse.setId(id);
        return caisse;
    }
}
