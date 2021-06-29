package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotAnnuel;
import com.growup.comptadecision.domain.ImpotAnnuelLigne_;
import com.growup.comptadecision.domain.ImpotAnnuel_;
import com.growup.comptadecision.repository.ImpotAnnuelRepository;
import com.growup.comptadecision.service.dto.ImpotAnnuelCriteria;
import com.growup.comptadecision.service.dto.ImpotAnnuelDTO;
import com.growup.comptadecision.service.mapper.ImpotAnnuelMapper;
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
 * Service for executing complex queries for ImpotAnnuel entities in the database.
 * The main input is a {@link ImpotAnnuelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ImpotAnnuelDTO} or a {@link Page} of {@link ImpotAnnuelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ImpotAnnuelQueryService extends QueryService<ImpotAnnuel> {

    private final Logger log = LoggerFactory.getLogger(ImpotAnnuelQueryService.class);

    private final ImpotAnnuelRepository impotAnnuelRepository;

    private final ImpotAnnuelMapper impotAnnuelMapper;

    public ImpotAnnuelQueryService(ImpotAnnuelRepository impotAnnuelRepository, ImpotAnnuelMapper impotAnnuelMapper) {
        this.impotAnnuelRepository = impotAnnuelRepository;
        this.impotAnnuelMapper = impotAnnuelMapper;
    }

    /**
     * Return a {@link List} of {@link ImpotAnnuelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ImpotAnnuelDTO> findByCriteria(ImpotAnnuelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ImpotAnnuel> specification = createSpecification(criteria);
        return impotAnnuelMapper.toDto(impotAnnuelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ImpotAnnuelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ImpotAnnuelDTO> findByCriteria(ImpotAnnuelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ImpotAnnuel> specification = createSpecification(criteria);
        return impotAnnuelRepository.findAll(specification, page)
            .map(impotAnnuelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ImpotAnnuelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ImpotAnnuel> specification = createSpecification(criteria);
        return impotAnnuelRepository.count(specification);
    }

    /**
     * Function to convert ImpotAnnuelCriteria to a {@link Specification}
     */
    private Specification<ImpotAnnuel> createSpecification(ImpotAnnuelCriteria criteria) {
        Specification<ImpotAnnuel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ImpotAnnuel_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ImpotAnnuel_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), ImpotAnnuel_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ImpotAnnuel_.description));
            }
            if (criteria.getCalcule() != null) {
                specification = specification.and(buildSpecification(criteria.getCalcule(), ImpotAnnuel_.calcule));
            }
            if (criteria.getTriOrdre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriOrdre(), ImpotAnnuel_.triOrdre));
            }
            if (criteria.getImpotAnnuelLigneId() != null) {
                specification = specification.and(buildSpecification(criteria.getImpotAnnuelLigneId(),
                    root -> root.join(ImpotAnnuel_.impotAnnuelLignes, JoinType.LEFT).get(ImpotAnnuelLigne_.id)));
            }
        }
        return specification;
    }
}
