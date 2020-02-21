package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.Cnss;
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

        return cnssDTO.getMontantSalaireBrutKarama()
                .add(cnssDTO.getMontantSalaireBrutNormal())
                .add(cnssDTO.getMontantSalaireBrutAutre());
    }

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    @Mapping(source = "ficheClient.dateCreation", target = "ficheClientDateCreation")
    CnssDTO toDto(Cnss cnss);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    @Mapping(target = "montantTotal", expression = "java(this.sumMontants(cnssDTO))")
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
