package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.CategorieCnssGerant;
import com.growup.comptadecision.domain.CategorieCnssGerant_;
import com.growup.comptadecision.repository.CategorieCnssGerantRepository;
import com.growup.comptadecision.service.dto.CategorieCnssGerantCriteria;
import com.growup.comptadecision.service.dto.CategorieCnssGerantDTO;
import com.growup.comptadecision.service.mapper.CategorieCnssGerantMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for CategorieCnssGerant entities in the database.
 * The main input is a {@link CategorieCnssGerantCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CategorieCnssGerantDTO} or a {@link Page} of {@link CategorieCnssGerantDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CategorieCnssGerantQueryService extends QueryService<CategorieCnssGerant> {

    private final Logger log = LoggerFactory.getLogger(CategorieCnssGerantQueryService.class);

    private final CategorieCnssGerantRepository categorieCnssGerantRepository;

    private final CategorieCnssGerantMapper categorieCnssGerantMapper;

    public CategorieCnssGerantQueryService(CategorieCnssGerantRepository categorieCnssGerantRepository, CategorieCnssGerantMapper categorieCnssGerantMapper) {
        this.categorieCnssGerantRepository = categorieCnssGerantRepository;
        this.categorieCnssGerantMapper = categorieCnssGerantMapper;
    }

    /**
     * Return a {@link List} of {@link CategorieCnssGerantDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CategorieCnssGerantDTO> findByCriteria(CategorieCnssGerantCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CategorieCnssGerant> specification = createSpecification(criteria);
        return categorieCnssGerantMapper.toDto(categorieCnssGerantRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CategorieCnssGerantDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CategorieCnssGerantDTO> findByCriteria(CategorieCnssGerantCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CategorieCnssGerant> specification = createSpecification(criteria);
        return categorieCnssGerantRepository.findAll(specification, page)
            .map(categorieCnssGerantMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CategorieCnssGerantCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CategorieCnssGerant> specification = createSpecification(criteria);
        return categorieCnssGerantRepository.count(specification);
    }

    /**
     * Function to convert CategorieCnssGerantCriteria to a {@link Specification}
     */
    private Specification<CategorieCnssGerant> createSpecification(CategorieCnssGerantCriteria criteria) {
        Specification<CategorieCnssGerant> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CategorieCnssGerant_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CategorieCnssGerant_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), CategorieCnssGerant_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CategorieCnssGerant_.description));
            }
            if (criteria.getMontantCotisationCnss() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantCotisationCnss(), CategorieCnssGerant_.montantCotisationCnss));
            }
        }
        return specification;
    }
}
