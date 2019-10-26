package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity QuittanceMensuelleImpotDetail and its DTO QuittanceMensuelleImpotDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleImpotMapper.class, ImpotMensuelMapper.class, QuittanceMensuelleImpotSousDetailMapper.class})
public interface QuittanceMensuelleImpotDetailMapper extends EntityMapper<QuittanceMensuelleImpotDetailDTO, QuittanceMensuelleImpotDetail> {

    QuittanceMensuelleImpotDetailDTO toDto(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail);

    @Mapping(target = "quittanceMensuelleImpotSousDetails", expression = "java(" +
            "quittanceMensuelleImpotDetailDTO.getQuittanceMensuelleImpotSousDetails().stream().map(quittanceMensuelleImpotSousDetailDTO ->  {" +
            "com.growup.comptadecision.domain.QuittanceMensuelleImpotSousDetail quittanceMensuelleImpotSousDetail = " +
            "quittanceMensuelleImpotSousDetailMapper.toEntity(quittanceMensuelleImpotSousDetailDTO);" +
            "quittanceMensuelleImpotSousDetail.setQuittanceMensuelleImpotDetail(quittanceMensuelleImpotDetail);" +
            "return quittanceMensuelleImpotSousDetail;})" +
            ".collect(java.util.stream.Collectors.toList()))")
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
