package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.CentreAdministratifMapper;
import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.service.dto.FicheClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity FicheClient and its DTO FicheClientDTO.
 */
@Mapper(componentModel = "spring", uses = {CategorieCnssGerantMapper.class, CentreAdministratifMapper.class, SecteurActiviteMapper.class, ActiviteMapper.class, RegionMapper.class, VilleMapper.class , ImpotMensuelClientMapper.class})
public interface FicheClientMapper extends EntityMapper<FicheClientDTO, FicheClient> {

    @Mapping(source = "activite1.id", target = "activite1Id")
    @Mapping(source = "activite1.libelle", target = "activite1Libelle")
    @Mapping(source = "activite2.id", target = "activite2Id")
    @Mapping(source = "activite2.libelle", target = "activite2Libelle")
    @Mapping(source = "activite3.id", target = "activite3Id")
    @Mapping(source = "activite3.libelle", target = "activite3Libelle")
    @Mapping(source = "secteurActivite1.id", target = "secteurActivite1Id")
    @Mapping(source = "secteurActivite1.libelle", target = "secteurActivite1Libelle") @Mapping(source = "secteurActivite2.id", target = "secteurActivite2Id")
    @Mapping(source = "secteurActivite2.libelle", target = "secteurActivite2Libelle")
    @Mapping(source = "secteurActivite3.id", target = "secteurActivite3Id")
    @Mapping(source = "secteurActivite3.libelle", target = "secteurActivite3Libelle")
    @Mapping(source = "impotMensuelClients", target = "impotMensuelClients")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.libelle", target = "regionLibelle")
    @Mapping(source = "ville.id", target = "villeId")
    @Mapping(source = "ville.libelle", target = "villeLibelle")
    @Mapping(source = "categorieCnssGerant.libelle", target = "categorieCnssGerantLibelle")
    @Mapping(source = "categorieCnssGerant.id", target = "categorieCnssGerantId")
    @Mapping(target = "tauxCnssAccident", expression = "java(ficheClient.getTauxCnssAccident() != null ? ficheClient.getTauxCnssAccident()/100 : null)")
    FicheClientDTO toDto(FicheClient ficheClient);

    @Mapping(source = "activite1Id", target = "activite1")
    @Mapping(source = "activite2Id", target = "activite2")
    @Mapping(source = "activite3Id", target = "activite3")
    @Mapping(source = "secteurActivite1Id", target = "secteurActivite1")
    @Mapping(source = "secteurActivite2Id", target = "secteurActivite2")
    @Mapping(source = "secteurActivite3Id", target = "secteurActivite3")
    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "villeId", target = "ville")
    @Mapping(source = "categorieCnssGerantId", target = "categorieCnssGerant")
    @Mapping(target = "tauxCnssAccident", expression = "java(ficheClientDTO.getTauxCnssAccident() != null ? ficheClientDTO.getTauxCnssAccident() * 100 : null)")
    @Mapping(target = "impotMensuelClients", expression = "java(" +
            "ficheClientDTO.getImpotMensuelClients().stream().map(impotMensuelClientDto ->  {" +
            "com.growup.comptadecision.domain.ImpotMensuelClient impotMensuelClient = impotMensuelClientMapper.toEntity(impotMensuelClientDto);" +
            "impotMensuelClient.setFicheClient(ficheClient);" +
            "return impotMensuelClient;})" +
            ".collect(java.util.stream.Collectors.toList()))")
    FicheClient toEntity(FicheClientDTO ficheClientDTO);

    default FicheClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        FicheClient ficheClient = new FicheClient();
        ficheClient.setId(id);
        return ficheClient;
    }
}
