package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.Cnss;
import com.growup.comptadecision.domain.enumeration.TypeCnss;
import com.growup.comptadecision.service.dto.CnssDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

/**
 * Mapper for the entity Cnss and its DTO CnssDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class})
public interface CnssMapper extends EntityMapper<CnssDTO, Cnss> {

    default BigDecimal sumMontants(CnssDTO cnssDTO) {

        if (cnssDTO.getTypeCnss() == TypeCnss.CNSS_EMPLOYEUR) {
            return cnssDTO.getMontantTotalCnss();
        } else {
            BigDecimal tauxCnss = cnssDTO.getTauxCnssNormalAccident().add(cnssDTO.getTauxCnssNormal());
            BigDecimal tauxCnssKarama = cnssDTO.getTauxCnssKarama().add(cnssDTO.getTauxCnssKarama());
            return cnssDTO.getMontantSalaireBrutNormal().multiply(tauxCnss)
                .add(cnssDTO.getMontantSalaireBrutKarama().multiply(tauxCnssKarama));
        }
    }

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    @Mapping(source = "ficheClient.dateCreation", target = "ficheClientDateCreation")
    @Mapping(source = "ficheClient.cnssGerant", target = "ficheClientCnssGerant")
    @Mapping(source = "ficheClient.cnssEmployeur", target = "ficheClientCnssEmployeur")
    CnssDTO toDto(Cnss cnss);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    @Mapping(target = "montantTotalCnss", expression = "java(this.sumMontants(cnssDTO))")
    @Mapping(target = "montantTotalSalaireBrut", expression = "java(this.sumMontants(cnssDTO))")
    Cnss toEntity(CnssDTO cnssDTO);

    default Cnss fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cnss cnss = new Cnss();
        cnss.setId(id);
        return cnss;
    }
}
