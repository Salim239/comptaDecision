package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotMensuel;
import com.growup.comptadecision.domain.ImpotMensuelLigne_;
import com.growup.comptadecision.domain.ImpotMensuel_;
import com.growup.comptadecision.repository.ImpotMensuelRepository;
import com.growup.comptadecision.service.dto.ImpotMensuelCriteria;
import com.growup.comptadecision.service.dto.ImpotMensuelDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelMapper;
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
 * Service for executing complex queries for ImpotMensuel entities in the database.
 * The main input is a {@link ImpotMensuelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ImpotMensuelDTO} or a {@link Page} of {@link ImpotMensuelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ImpotMensuelQueryService extends QueryService<ImpotMensuel> {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelQueryService.class);

    private final ImpotMensuelRepository impotMensuelRepository;

    private final ImpotMensuelMapper impotMensuelMapper;

    public ImpotMensuelQueryService(ImpotMensuelRepository impotMensuelRepository, ImpotMensuelMapper impotMensuelMapper) {
        this.impotMensuelRepository = impotMensuelRepository;
        this.impotMensuelMapper = impotMensuelMapper;
    }

    /**
     * Return a {@link List} of {@link ImpotMensuelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ImpotMensuelDTO> findByCriteria(ImpotMensuelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ImpotMensuel> specification = createSpecification(criteria);
        return impotMensuelMapper.toDto(impotMensuelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ImpotMensuelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ImpotMensuelDTO> findByCriteria(ImpotMensuelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ImpotMensuel> specification = createSpecification(criteria);
        return impotMensuelRepository.findAll(specification, page)
            .map(impotMensuelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ImpotMensuelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ImpotMensuel> specification = createSpecification(criteria);
        return impotMensuelRepository.count(specification);
    }

    /**
     * Function to convert ImpotMensuelCriteria to a {@link Specification}
     */
    private Specification<ImpotMensuel> createSpecification(ImpotMensuelCriteria criteria) {
        Specification<ImpotMensuel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ImpotMensuel_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ImpotMensuel_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), ImpotMensuel_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ImpotMensuel_.description));
            }
            if (criteria.getParent() != null) {
                specification = specification.and(buildSpecification(criteria.getParent(), ImpotMensuel_.parent));
            }
            if (criteria.getChild() != null) {
                specification = specification.and(buildSpecification(criteria.getChild(), ImpotMensuel_.child));
            }
            if (criteria.getAppliquerReportMontant() != null) {
                specification = specification.and(buildSpecification(criteria.getAppliquerReportMontant(), ImpotMensuel_.appliquerReportMontant));
            }
            if (criteria.getCoefficientMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoefficientMontant(), ImpotMensuel_.coefficientMontant));
            }
            if (criteria.getImpotMensuelLigneId() != null) {
                specification = specification.and(buildSpecification(criteria.getImpotMensuelLigneId(),
                    root -> root.join(ImpotMensuel_.impotMensuelLignes, JoinType.LEFT).get(ImpotMensuelLigne_.id)));
            }
            if (criteria.getChildImpotMensuelId() != null) {
                specification = specification.and(buildSpecification(criteria.getChildImpotMensuelId(),
                    root -> root.join(ImpotMensuel_.childImpotMensuels, JoinType.LEFT).get(ImpotMensuel_.id)));
            }
            if (criteria.getParentImpotMensuelId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentImpotMensuelId(),
                    root -> root.join(ImpotMensuel_.parentImpotMensuel, JoinType.LEFT).get(ImpotMensuel_.id)));
            }
        }
        return specification;
    }
}
