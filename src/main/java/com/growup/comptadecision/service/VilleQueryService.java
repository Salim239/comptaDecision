package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Region_;
import com.growup.comptadecision.domain.Ville;
import com.growup.comptadecision.domain.Ville_;
import com.growup.comptadecision.repository.VilleRepository;
import com.growup.comptadecision.service.dto.VilleCriteria;
import com.growup.comptadecision.service.dto.VilleDTO;
import com.growup.comptadecision.service.mapper.VilleMapper;
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
 * Service for executing complex queries for Ville entities in the database.
 * The main input is a {@link VilleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VilleDTO} or a {@link Page} of {@link VilleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VilleQueryService extends QueryService<Ville> {

    private final Logger log = LoggerFactory.getLogger(VilleQueryService.class);

    private final VilleRepository villeRepository;

    private final VilleMapper villeMapper;

    public VilleQueryService(VilleRepository villeRepository, VilleMapper villeMapper) {
        this.villeRepository = villeRepository;
        this.villeMapper = villeMapper;
    }

    /**
     * Return a {@link List} of {@link VilleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VilleDTO> findByCriteria(VilleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Ville> specification = createSpecification(criteria);
        return villeMapper.toDto(villeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VilleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VilleDTO> findByCriteria(VilleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Ville> specification = createSpecification(criteria);
        return villeRepository.findAll(specification, page)
            .map(villeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VilleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Ville> specification = createSpecification(criteria);
        return villeRepository.count(specification);
    }

    /**
     * Function to convert VilleCriteria to a {@link Specification}
     */
    private Specification<Ville> createSpecification(VilleCriteria criteria) {
        Specification<Ville> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Ville_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Ville_.code));
            }
            if (criteria.getCodePostal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodePostal(), Ville_.codePostal));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Ville_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Ville_.description));
            }
            if (criteria.getRegionId() != null) {
                specification = specification.and(buildSpecification(criteria.getRegionId(),
                    root -> root.join(Ville_.region, JoinType.LEFT).get(Region_.id)));
            }
        }
        return specification;
    }
}
