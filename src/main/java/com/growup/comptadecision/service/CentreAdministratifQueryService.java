package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.repository.CentreAdministratifRepository;
import com.growup.comptadecision.service.dto.CentreAdministratifCriteria;
import com.growup.comptadecision.service.dto.CentreAdministratifDTO;
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
 * Service for executing complex queries for CentreAdministratif entities in the database.
 * The main input is a {@link CentreAdministratifCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CentreAdministratifDTO} or a {@link Page} of {@link CentreAdministratifDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CentreAdministratifQueryService extends QueryService<CentreAdministratif> {

    private final Logger log = LoggerFactory.getLogger(CentreAdministratifQueryService.class);

    private final CentreAdministratifRepository centreAdministratifRepository;

    private final CentreAdministratifMapper centreAdministratifMapper;

    public CentreAdministratifQueryService(CentreAdministratifRepository centreAdministratifRepository, CentreAdministratifMapper centreAdministratifMapper) {
        this.centreAdministratifRepository = centreAdministratifRepository;
        this.centreAdministratifMapper = centreAdministratifMapper;
    }

    /**
     * Return a {@link List} of {@link CentreAdministratifDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CentreAdministratifDTO> findByCriteria(CentreAdministratifCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CentreAdministratif> specification = createSpecification(criteria);
        return centreAdministratifMapper.toDto(centreAdministratifRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CentreAdministratifDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CentreAdministratifDTO> findByCriteria(CentreAdministratifCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CentreAdministratif> specification = createSpecification(criteria);
        return centreAdministratifRepository.findAll(specification, page)
            .map(centreAdministratifMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CentreAdministratifCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CentreAdministratif> specification = createSpecification(criteria);
        return centreAdministratifRepository.count(specification);
    }

    /**
     * Function to convert CentreAdministratifCriteria to a {@link Specification}
     */
    private Specification<CentreAdministratif> createSpecification(CentreAdministratifCriteria criteria) {
        Specification<CentreAdministratif> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CentreAdministratif_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), CentreAdministratif_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CentreAdministratif_.description));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildSpecification(criteria.getType(), CentreAdministratif_.type));
            }
            if (criteria.getTelephone1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone1(), CentreAdministratif_.telephone1));
            }
            if (criteria.getTelephone2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone2(), CentreAdministratif_.telephone2));
            }
            if (criteria.getTelephone3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone3(), CentreAdministratif_.telephone3));
            }
            if (criteria.getEmail1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail1(), CentreAdministratif_.email1));
            }
            if (criteria.getEmail2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail2(), CentreAdministratif_.email2));
            }
            if (criteria.getEmail3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail3(), CentreAdministratif_.email3));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), CentreAdministratif_.fax));
            }
            if (criteria.getRib() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRib(), CentreAdministratif_.rib));
            }
            if (criteria.getBanque() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBanque(), CentreAdministratif_.banque));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), CentreAdministratif_.adresse));
            }
            if (criteria.getCodePostal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodePostal(), CentreAdministratif_.codePostal));
            }
            if (criteria.getVilleId() != null) {
                specification = specification.and(buildSpecification(criteria.getVilleId(),
                    root -> root.join(CentreAdministratif_.ville, JoinType.LEFT).get(Ville_.id)));
            }
            if (criteria.getRegionId() != null) {
                specification = specification.and(buildSpecification(criteria.getRegionId(),
                    root -> root.join(CentreAdministratif_.region, JoinType.LEFT).get(Region_.id)));
            }
        }
        return specification;
    }
}
