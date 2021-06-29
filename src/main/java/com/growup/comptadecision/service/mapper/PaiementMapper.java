package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.Paiement;
import com.growup.comptadecision.service.dto.PaiementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Paiement and its DTO PaiementDTO.
 */
@Mapper(componentModel = "spring", uses = {LigneCaisseMapper.class, FicheClientMapper.class, CnssMapper.class, QuittanceMensuelleMapper.class, DeclarationAnnuelleMapper.class, DeclarationEmployeurAnnuelleMapper.class, AcompteProvisionnelMapper.class})
public interface PaiementMapper extends EntityMapper<PaiementDTO, Paiement> {

    @Mapping(source = "ligneCaisse.id", target = "ligneCaisseId")
    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "cnss.id", target = "cnssId")
    @Mapping(source = "quittanceMensuelle.id", target = "quittanceMensuelleId")
    @Mapping(source = "declarationAnnuelle.id", target = "declarationAnnuelleId")
    @Mapping(source = "declarationEmployeurAnnuelle.id", target = "declarationEmployeurAnnuelleId")
    @Mapping(source = "acompteProvisionnel.id", target = "acompteProvisionnelId")
    PaiementDTO toDto(Paiement paiement);

    @Mapping(source = "ligneCaisseId", target = "ligneCaisse")
    @Mapping(source = "ficheClientId", target = "ficheClient")
    @Mapping(source = "cnssId", target = "cnss")
    @Mapping(source = "quittanceMensuelleId", target = "quittanceMensuelle")
    @Mapping(source = "declarationAnnuelleId", target = "declarationAnnuelle")
    @Mapping(source = "declarationEmployeurAnnuelleId", target = "declarationEmployeurAnnuelle")
    @Mapping(source = "acompteProvisionnelId", target = "acompteProvisionnel")
    Paiement toEntity(PaiementDTO paiementDTO);

    default Paiement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paiement paiement = new Paiement();
        paiement.setId(id);
        return paiement;
    }
}
