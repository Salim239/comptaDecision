package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.service.dto.FicheClientCriteria;
import com.growup.comptadecision.service.dto.FicheClientDTO;
import com.growup.comptadecision.service.mapper.FicheClientMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for FicheClient entities in the database.
 * The main input is a {@link FicheClientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FicheClientDTO} or a {@link Page} of {@link FicheClientDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FicheClientQueryService extends QueryService<FicheClient> {

    private final Logger log = LoggerFactory.getLogger(FicheClientQueryService.class);

    private final FicheClientRepository ficheClientRepository;

    private final FicheClientMapper ficheClientMapper;

    public FicheClientQueryService(FicheClientRepository ficheClientRepository, FicheClientMapper ficheClientMapper) {
        this.ficheClientRepository = ficheClientRepository;
        this.ficheClientMapper = ficheClientMapper;
    }

    /**
     * Return a {@link List} of {@link FicheClientDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FicheClientDTO> findByCriteria(FicheClientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FicheClient> specification = createSpecification(criteria);
        return ficheClientMapper.toDto(ficheClientRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FicheClientDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FicheClientDTO> findByCriteria(FicheClientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FicheClient> specification = createSpecification(criteria);
        return ficheClientRepository.findAll(specification, page)
            .map(ficheClientMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FicheClientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FicheClient> specification = createSpecification(criteria);
        return ficheClientRepository.count(specification);
    }

    /**
     * Function to convert FicheClientCriteria to a {@link Specification}
     */
    private Specification<FicheClient> createSpecification(FicheClientCriteria criteria) {
        Specification<FicheClient> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FicheClient_.id));
            }
            if (criteria.getCategorieClient() != null) {
                specification = specification.and(buildSpecification(criteria.getCategorieClient(), FicheClient_.categorieClient));
            }
            if (criteria.getDesignation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesignation(), FicheClient_.designation));
            }
            if (criteria.getPrenomGerant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenomGerant(), FicheClient_.prenomGerant));
            }
            if (criteria.getNomGerant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNomGerant(), FicheClient_.nomGerant));
            }
            if (criteria.getDateNaissanceGerant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateNaissanceGerant(), FicheClient_.dateNaissanceGerant));
            }
            if (criteria.getAdresseGerant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresseGerant(), FicheClient_.adresseGerant));
            }
            if (criteria.getTelephoneGerant1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephoneGerant1(), FicheClient_.telephoneGerant1));
            }
            if (criteria.getTelephoneGerant2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephoneGerant2(), FicheClient_.telephoneGerant2));
            }
            if (criteria.getEmailGerant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailGerant(), FicheClient_.emailGerant));
            }
            if (criteria.getCinGerant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCinGerant(), FicheClient_.cinGerant));
            }
            if (criteria.getDateDelivranceCINGerant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateDelivranceCINGerant(), FicheClient_.dateDelivranceCINGerant));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), FicheClient_.adresse));
            }
            if (criteria.getCodePostal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodePostal(), FicheClient_.codePostal));
            }
            if (criteria.getTelephone1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone1(), FicheClient_.telephone));
            }
            if (criteria.getTelephone2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone2(), FicheClient_.telephone2));
            }
            if (criteria.getTelephone3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone3(), FicheClient_.telephone3));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), FicheClient_.fax));
            }
            if (criteria.getEmail1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail1(), FicheClient_.email));
            }
            if (criteria.getEmail2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail2(), FicheClient_.email2));
            }
            if (criteria.getEmail3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail3(), FicheClient_.email3));
            }
            if (criteria.getMatriculeFiscale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatriculeFiscale(), FicheClient_.matriculeFiscale));
            }
            if (criteria.getRegistreCommerce() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegistreCommerce(), FicheClient_.registreCommerce));
            }
            if (criteria.getDateCreation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreation(), FicheClient_.dateCreation));
            }
            if (criteria.getCnssEmployeur() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCnssEmployeur(), FicheClient_.cnssEmployeur));
            }
            if (criteria.getCnssGerant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCnssGerant(), FicheClient_.cnssGerant));
            }
            if (criteria.getCategorieActivite() != null) {
                specification = specification.and(buildSpecification(criteria.getCategorieActivite(), FicheClient_.categorieActivite));
            }
            if (criteria.getCodeTVA() != null) {
                specification = specification.and(buildSpecification(criteria.getCodeTVA(), FicheClient_.codeTva));
            }
            if (criteria.getNumeroEtablissementSecondaire() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroEtablissementSecondaire(), FicheClient_.numeroEtablissementSecondaire));
            }
            if (criteria.getTauxCnssAccident() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxCnssAccident(), FicheClient_.tauxCnssAccident));
            }
            if (criteria.getImpotMensuelClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getImpotMensuelClientId(),
                    root -> root.join(FicheClient_.impotMensuelClients, JoinType.LEFT).get(ImpotMensuelClient_.id)));
            }
            if (criteria.getActivite1Id() != null) {
                specification = specification.and(buildSpecification(criteria.getActivite1Id(),
                    root -> root.join(FicheClient_.activite1, JoinType.LEFT).get(Activite_.id)));
            }
            if (criteria.getActivite2Id() != null) {
                specification = specification.and(buildSpecification(criteria.getActivite2Id(),
                    root -> root.join(FicheClient_.activite2, JoinType.LEFT).get(Activite_.id)));
            }
            if (criteria.getActivite3Id() != null) {
                specification = specification.and(buildSpecification(criteria.getActivite3Id(),
                    root -> root.join(FicheClient_.activite3, JoinType.LEFT).get(Activite_.id)));
            }
            if (criteria.getSecteurActivite1Id() != null) {
                specification = specification.and(buildSpecification(criteria.getSecteurActivite1Id(),
                    root -> root.join(FicheClient_.secteurActivite1, JoinType.LEFT).get(SecteurActivite_.id)));
            }
            if (criteria.getSecteurActivite2Id() != null) {
                specification = specification.and(buildSpecification(criteria.getSecteurActivite2Id(),
                    root -> root.join(FicheClient_.secteurActivite2, JoinType.LEFT).get(SecteurActivite_.id)));
            }
            if (criteria.getSecteurActivite3Id() != null) {
                specification = specification.and(buildSpecification(criteria.getSecteurActivite3Id(),
                    root -> root.join(FicheClient_.secteurActivite3, JoinType.LEFT).get(SecteurActivite_.id)));
            }
            if (criteria.getRegionId() != null) {
                specification = specification.and(buildSpecification(criteria.getRegionId(),
                    root -> root.join(FicheClient_.region, JoinType.LEFT).get(Region_.id)));
            }
            if (criteria.getVilleId() != null) {
                specification = specification.and(buildSpecification(criteria.getVilleId(),
                    root -> root.join(FicheClient_.ville, JoinType.LEFT).get(Ville_.id)));
            }
            if (criteria.getCategorieCnssGerantId() != null) {
                specification = specification.and(buildSpecification(criteria.getCategorieCnssGerantId(),
                    root -> root.join(FicheClient_.categorieCnssGerant, JoinType.LEFT).get(CategorieCnssGerant_.id)));
            }
            if (criteria.getComptableClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getComptableClientId(),
                    root -> root.join(FicheClient_.cabinetComptable, JoinType.LEFT).get(CabinetComptable_.id)));
            }
        }
        return specification;
    }
}
