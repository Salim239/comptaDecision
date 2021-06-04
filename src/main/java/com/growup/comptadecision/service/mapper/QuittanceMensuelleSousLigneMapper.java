package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotMensuelLigne;
import com.growup.comptadecision.domain.QuittanceMensuelleLigne;
import com.growup.comptadecision.domain.QuittanceMensuelleSousLigne;
import com.growup.comptadecision.domain.enumeration.TypeValeur;
import com.growup.comptadecision.service.dto.QuittanceMensuelleSousLigneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for the entity QuittanceMensuelleLigne and its DTO QuittanceMensuelleLigneDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleLigneMapper.class, ImpotMensuelLigneMapper.class})
public interface QuittanceMensuelleSousLigneMapper extends EntityMapper<QuittanceMensuelleSousLigneDTO, QuittanceMensuelleSousLigne> {

    default BigDecimal sum(QuittanceMensuelleSousLigneDTO quittanceMensuelleSousLigneImpot) {

        if (quittanceMensuelleSousLigneImpot.getMontantBase() == null) return BigDecimal.ZERO;

        float impot = quittanceMensuelleSousLigneImpot.getImpotMensuelLigneTypeValeur() == TypeValeur.MONTANT ?
                quittanceMensuelleSousLigneImpot.getImpotMensuelLigneValeur() :
                quittanceMensuelleSousLigneImpot.getImpotMensuelLigneValeur() / 100;
        MathContext mc3 = new MathContext(3);
        return quittanceMensuelleSousLigneImpot.getMontantBase().multiply(new BigDecimal(impot, mc3));
    }

    default BigDecimal getMontantTotal(QuittanceMensuelleSousLigneDTO sousLigneDTO) {

        return sousLigneDTO.getImpotMensuelLigneValeurModifiable() ? sousLigneDTO.getMontantTotal() : sum(sousLigneDTO);
    }

    @Mapping(source = "impotMensuelLigne.id", target = "impotMensuelLigneId")
    @Mapping(source = "impotMensuelLigne.code", target = "impotMensuelLigneCode")
    @Mapping(source = "impotMensuelLigne.triOrdre", target = "impotMensuelLigneTriOrdre")
    @Mapping(source = "impotMensuelLigne.libelle", target = "impotMensuelLigneLibelle")
    @Mapping(source = "impotMensuelLigne.description", target = "impotMensuelLigneDescription")
    @Mapping(source = "impotMensuelLigne.typeValeur", target = "impotMensuelLigneTypeValeur")
    @Mapping(source = "impotMensuelLigne.valeur", target = "impotMensuelLigneValeur")
    @Mapping(source = "impotMensuelLigne.valeurModifiable", target = "impotMensuelLigneValeurModifiable")
    QuittanceMensuelleSousLigneDTO toDto(QuittanceMensuelleSousLigne quittanceMensuelleSousLigne);

    @Mapping(target = "impotMensuelLigne.id", source = "impotMensuelLigneId")
    @Mapping(target = "impotMensuelLigne.code", source = "impotMensuelLigneCode")
    @Mapping(target = "impotMensuelLigne.triOrdre", source = "impotMensuelLigneTriOrdre")
    @Mapping(target = "impotMensuelLigne.libelle", source = "impotMensuelLigneLibelle")
    @Mapping(target = "impotMensuelLigne.description", source = "impotMensuelLigneDescription")
    @Mapping(target = "impotMensuelLigne.typeValeur", source = "impotMensuelLigneTypeValeur")
    @Mapping(target = "impotMensuelLigne.valeur", source = "impotMensuelLigneValeur")
    @Mapping(target = "impotMensuelLigne.valeurModifiable", source = "impotMensuelLigneValeurModifiable")
    @Mapping(target = "montantTotal", expression = "java(getMontantTotal(quittanceMensuelleSousLigneDTO))")
    QuittanceMensuelleSousLigne toEntity(QuittanceMensuelleSousLigneDTO quittanceMensuelleSousLigneDTO);

    default QuittanceMensuelleSousLigne fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelleSousLigne quittanceMensuelleSousLigne = new QuittanceMensuelleSousLigne();
        quittanceMensuelleSousLigne.setId(id);
        return quittanceMensuelleSousLigne;
    }

    default QuittanceMensuelleSousLigne map(QuittanceMensuelleLigne quittanceMensuelleLigne, ImpotMensuelLigne impotMensuelLigne) {
        QuittanceMensuelleSousLigne quittanceMensuelleSousLigne = new QuittanceMensuelleSousLigne();
        quittanceMensuelleSousLigne.setImpotMensuelLigne(impotMensuelLigne);
        quittanceMensuelleSousLigne.setQuittanceMensuelleLigne(quittanceMensuelleLigne);
        return quittanceMensuelleSousLigne;
    }

    default List<QuittanceMensuelleSousLigne> map(QuittanceMensuelleLigne quittanceMensuelleLigne, List<ImpotMensuelLigne> impotMensuelLignes) {

        return impotMensuelLignes.stream().map(impotMensuelLigne -> map(quittanceMensuelleLigne, impotMensuelLigne))
        .collect(Collectors.toList());
    }
}
