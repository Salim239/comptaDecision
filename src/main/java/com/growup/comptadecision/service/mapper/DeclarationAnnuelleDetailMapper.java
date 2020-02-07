package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.DecalrationAnnuelleDetail;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity DeclarationAnnuelle and its DTO DeclarationAnnuelleDTO.
 */
@Mapper(componentModel = "spring", uses = {DeclarationAnnuelleMapper.class, ImpotAnnuelMapper.class})
public interface DeclarationAnnuelleDetailMapper extends EntityMapper<DeclarationAnnuelleDetailDTO, DecalrationAnnuelleDetail> {



    DeclarationAnnuelleDetailDTO toDto(DecalrationAnnuelleDetail decalrationAnnuelleDetail);


    DecalrationAnnuelleDetail toEntity(DeclarationAnnuelleDetailDTO declarationAnnuelleDetailDTO);

    default DecalrationAnnuelleDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        DecalrationAnnuelleDetail decalrationAnnuelleDetail = new DecalrationAnnuelleDetail();
        decalrationAnnuelleDetail.setId(id);
        return decalrationAnnuelleDetail;
    }
}
