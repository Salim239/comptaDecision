package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotMensuelLigne;
import com.growup.comptadecision.domain.ImpotMensuelLigne_;
import com.growup.comptadecision.domain.ImpotMensuel_;
import com.growup.comptadecision.repository.ImpotMensuelLigneRepository;
import com.growup.comptadecision.service.dto.ImpotMensuelLigneCriteria;
import com.growup.comptadecision.service.dto.ImpotMensuelLigneDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelLigneMapper;
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
 * Service for executing complex queries for ImpotMensuelLigne entities in the database.
 * The main input is a {@link ImpotMensuelLigneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ImpotMensuelLigneDTO} or a {@link Page} of {@link ImpotMensuelLigneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ImpotMensuelLigneQueryService extends QueryService<ImpotMensuelLigne> {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelLigneQueryService.class);

    private final ImpotMensuelLigneRepository impotMensuelLigneRepository;

    private final ImpotMensuelLigneMapper impotMensuelLigneMapper;

    public ImpotMensuelLigneQueryService(ImpotMensuelLigneRepository impotMensuelLigneRepository, ImpotMensuelLigneMapper impotMensuelLigneMapper) {
        this.impotMensuelLigneRepository = impotMensuelLigneRepository;
        this.impotMensuelLigneMapper = impotMensuelLigneMapper;
    }

    /**
     * Return a {@link List} of {@link ImpotMensuelLigneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ImpotMensuelLigneDTO> findByCriteria(ImpotMensuelLigneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ImpotMensuelLigne> specification = createSpecification(criteria);
        return impotMensuelLigneMapper.toDto(impotMensuelLigneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ImpotMensuelLigneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ImpotMensuelLigneDTO> findByCriteria(ImpotMensuelLigneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ImpotMensuelLigne> specification = createSpecification(criteria);
        return impotMensuelLigneRepository.findAll(specification, page)
            .map(impotMensuelLigneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ImpotMensuelLigneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ImpotMensuelLigne> specification = createSpecification(criteria);
        return impotMensuelLigneRepository.count(specification);
    }

    /**
     * Function to convert ImpotMensuelLigneCriteria to a {@link Specification}
     */
    private Specification<ImpotMensuelLigne> createSpecification(ImpotMensuelLigneCriteria criteria) {
        Specification<ImpotMensuelLigne> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ImpotMensuelLigne_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ImpotMensuelLigne_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), ImpotMensuelLigne_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ImpotMensuelLigne_.description));
            }
            if (criteria.getTriOrdre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriOrdre(), ImpotMensuelLigne_.triOrdre));
            }
            if (criteria.getValeur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValeur(), ImpotMensuelLigne_.valeur));
            }
            if (criteria.getTypeValeur() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeValeur(), ImpotMensuelLigne_.typeValeur));
            }
            if (criteria.getValeurModifiable() != null) {
                specification = specification.and(buildSpecification(criteria.getValeurModifiable(), ImpotMensuelLigne_.valeurModifiable));
            }
            if (criteria.getImpotMensuelId() != null) {
                specification = specification.and(buildSpecification(criteria.getImpotMensuelId(),
                    root -> root.join(ImpotMensuelLigne_.impotMensuel, JoinType.LEFT).get(ImpotMensuel_.id)));
            }
        }
        return specification;
    }
}
