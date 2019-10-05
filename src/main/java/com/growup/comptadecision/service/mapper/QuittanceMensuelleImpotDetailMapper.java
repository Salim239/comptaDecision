package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity QuittanceMensuelleImpotDetail and its DTO QuittanceMensuelleImpotDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleImpotMapper.class, ImpotMensuelClientMapper.class})
public interface QuittanceMensuelleImpotDetailMapper extends EntityMapper<QuittanceMensuelleImpotDetailDTO, QuittanceMensuelleImpotDetail> {

    QuittanceMensuelleImpotDetailDTO toDto(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail);


//    @Mapping(source = "quittanceMensuelleImpotId", target = "quittanceMensuelleImpot")
//    @Mapping(target = "quittanceMensuelleImpot",
//            expression = "java(quittanceMensuelleImpotDetailDTO.quittanceMensuelleImpotId() == null ? null : new com.growup.comptadecision.domain.QuittanceMensuelleImpot(quittanceMensuelleImpotDetailDTO.quittanceMensuelleImpotId()()))" )
    QuittanceMensuelleImpotDetail toEntity(QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO);

    default QuittanceMensuelleImpotDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail = new QuittanceMensuelleImpotDetail();
        quittanceMensuelleImpotDetail.setId(id);
        return quittanceMensuelleImpotDetail;
    }
}
