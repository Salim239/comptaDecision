package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ApplicationUserFicheClient;
import com.growup.comptadecision.service.dto.ApplicationUserFicheClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link ApplicationUserFicheClient} and its DTO {@link ApplicationUserFicheClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class, ApplicationUserMapper.class})
public interface ApplicationUserFicheClientMapper extends EntityMapper<ApplicationUserFicheClientDTO, ApplicationUserFicheClient> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "applicationUser.id", target = "applicationUserId")
    ApplicationUserFicheClientDTO toDto(ApplicationUserFicheClient applicationUserFicheClient);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    @Mapping(source = "applicationUserId", target = "applicationUser")
    ApplicationUserFicheClient toEntity(ApplicationUserFicheClientDTO applicationUserFicheClientDTO);

    default ApplicationUserFicheClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationUserFicheClient applicationUserFicheClient = new ApplicationUserFicheClient();
        applicationUserFicheClient.setId(id);
        return applicationUserFicheClient;
    }
}
