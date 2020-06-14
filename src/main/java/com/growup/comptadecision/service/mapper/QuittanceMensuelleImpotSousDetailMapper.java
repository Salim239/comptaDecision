package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotMensuelDetail;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotSousDetail;
import com.growup.comptadecision.domain.enumeration.TypeValeur;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotSousDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for the entity QuittanceMensuelleImpotDetail and its DTO QuittanceMensuelleImpotDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleImpotDetailMapper.class, ImpotMensuelDetailMapper.class})
public interface QuittanceMensuelleImpotSousDetailMapper extends EntityMapper<QuittanceMensuelleImpotSousDetailDTO, QuittanceMensuelleImpotSousDetail> {

    default BigDecimal sum(QuittanceMensuelleImpotSousDetailDTO quittanceMensuelleSousDetailImpot) {

        if (quittanceMensuelleSousDetailImpot.getMontantBase() == null) return BigDecimal.ZERO;

        float impot = quittanceMensuelleSousDetailImpot.getImpotMensuelDetailTypeValeur() == TypeValeur.MONTANT ?
                quittanceMensuelleSousDetailImpot.getImpotMensuelDetailValeur() :
                quittanceMensuelleSousDetailImpot.getImpotMensuelDetailValeur() / 100;
        MathContext mc3 = new MathContext(3);
        return quittanceMensuelleSousDetailImpot.getMontantBase().multiply(new BigDecimal(impot, mc3));
    }

    default BigDecimal getMontantTotal(QuittanceMensuelleImpotSousDetailDTO sousDetailDTO) {

        return sousDetailDTO.getImpotMensuelDetailValeurModifiable() ? sousDetailDTO.getMontantTotal() : sum(sousDetailDTO);
    }

    @Mapping(source = "impotMensuelDetail.id", target = "impotMensuelDetailId")
    @Mapping(source = "impotMensuelDetail.code", target = "impotMensuelDetailCode")
    @Mapping(source = "impotMensuelDetail.triOrdre", target = "impotMensuelDetailTriOrdre")
    @Mapping(source = "impotMensuelDetail.libelle", target = "impotMensuelDetailLibelle")
    @Mapping(source = "impotMensuelDetail.description", target = "impotMensuelDetailDescription")
    @Mapping(source = "impotMensuelDetail.typeValeur", target = "impotMensuelDetailTypeValeur")
    @Mapping(source = "impotMensuelDetail.valeur", target = "impotMensuelDetailValeur")
    @Mapping(source = "impotMensuelDetail.valeurModifiable", target = "impotMensuelDetailValeurModifiable")
    QuittanceMensuelleImpotSousDetailDTO toDto(QuittanceMensuelleImpotSousDetail quittanceMensuelleImpotSousDetail);

    @Mapping(target = "impotMensuelDetail.id", source = "impotMensuelDetailId")
    @Mapping(target = "impotMensuelDetail.code", source = "impotMensuelDetailCode")
    @Mapping(target = "impotMensuelDetail.triOrdre", source = "impotMensuelDetailTriOrdre")
    @Mapping(target = "impotMensuelDetail.libelle", source = "impotMensuelDetailLibelle")
    @Mapping(target = "impotMensuelDetail.description", source = "impotMensuelDetailDescription")
    @Mapping(target = "impotMensuelDetail.typeValeur", source = "impotMensuelDetailTypeValeur")
    @Mapping(target = "impotMensuelDetail.valeur", source = "impotMensuelDetailValeur")
    @Mapping(target = "impotMensuelDetail.valeurModifiable", source = "impotMensuelDetailValeurModifiable")
    @Mapping(target = "montantTotal", expression = "java(getMontantTotal(quittanceMensuelleImpotSousDetailDTO))")
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
