package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.CabinetComptable;
import com.growup.comptadecision.service.dto.CabinetComptableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link CabinetComptable} and its DTO {@link CabinetComptableDTO}.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class})
public interface CabinetComptableMapper extends EntityMapper<CabinetComptableDTO, CabinetComptable> {

    @Mapping(source = "ficheClientCabinet.id", target = "ficheClientCabinetId")
    CabinetComptableDTO toDto(CabinetComptable cabinetComptable);

    @Mapping(source = "ficheClientCabinetId", target = "ficheClientCabinet")
    @Mapping(target = "clients", ignore = true)
    CabinetComptable toEntity(CabinetComptableDTO cabinetComptableDTO);

    default CabinetComptable fromId(Long id) {
        if (id == null) {
            return null;
        }
        CabinetComptable cabinetComptable = new CabinetComptable();
        cabinetComptable.setId(id);
        return cabinetComptable;
    }
}
