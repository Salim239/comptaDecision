package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotMensuelDetail;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotSousDetail;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotSousDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for the entity QuittanceMensuelleImpotDetail and its DTO QuittanceMensuelleImpotDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleImpotDetailMapper.class, ImpotMensuelDetailMapper.class})
public interface QuittanceMensuelleImpotSousDetailMapper extends EntityMapper<QuittanceMensuelleImpotSousDetailDTO, QuittanceMensuelleImpotSousDetail> {

    @Mapping(source = "impotMensuelDetail.id", target = "impotMensuelDetailId")
    @Mapping(source = "impotMensuelDetail.code", target = "impotMensuelDetailCode")
    @Mapping(source = "impotMensuelDetail.triOrdre", target = "impotMensuelDetailTriOrdre")
    @Mapping(source = "impotMensuelDetail.libelle", target = "impotMensuelDetailLibelle")
    @Mapping(source = "impotMensuelDetail.description", target = "impotMensuelDetailDescription")
    @Mapping(source = "impotMensuelDetail.typeValeur", target = "impotMensuelDetailTypeValeur")
    @Mapping(source = "impotMensuelDetail.valeur", target = "impotMensuelDetailValeur")
    @Mapping(source = "impotMensuelDetail.valeurModifiable", target = "impotMensuelDetailValeurModifiable")
    QuittanceMensuelleImpotSousDetailDTO toDto(QuittanceMensuelleImpotSousDetail quittanceMensuelleImpotSousDetail);

    QuittanceMensuelleImpotSousDetail toEntity(QuittanceMensuelleImpotSousDetailDTO quittanceMensuelleImpotSousDetailDTO);

    default QuittanceMensuelleImpotSousDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelleImpotSousDetail quittanceMensuelleImpotSousDetail = new QuittanceMensuelleImpotSousDetail();
        quittanceMensuelleImpotSousDetail.setId(id);
        return quittanceMensuelleImpotSousDetail;
    }

    default QuittanceMensuelleImpotSousDetail map(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail, ImpotMensuelDetail impotMensuelDetail) {
        QuittanceMensuelleImpotSousDetail quittanceMensuelleImpotSousDetail = new QuittanceMensuelleImpotSousDetail();
        quittanceMensuelleImpotSousDetail.setImpotMensuelDetail(impotMensuelDetail);
        quittanceMensuelleImpotSousDetail.setQuittanceMensuelleImpotDetail(quittanceMensuelleImpotDetail);
        return quittanceMensuelleImpotSousDetail;
    }

    default List<QuittanceMensuelleImpotSousDetail> map(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail, List<ImpotMensuelDetail> impotMensuelDetails) {

        return impotMensuelDetails.stream().map(impotMensuelDetail -> map(quittanceMensuelleImpotDetail, impotMensuelDetail))
        .collect(Collectors.toList());
    }
}
