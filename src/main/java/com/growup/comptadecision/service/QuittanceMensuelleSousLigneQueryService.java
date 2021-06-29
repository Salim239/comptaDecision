package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotMensuelLigne_;
import com.growup.comptadecision.domain.QuittanceMensuelleLigne_;
import com.growup.comptadecision.domain.QuittanceMensuelleSousLigne;
import com.growup.comptadecision.domain.QuittanceMensuelleSousLigne_;
import com.growup.comptadecision.repository.QuittanceMensuelleSousLigneRepository;
import com.growup.comptadecision.service.dto.QuittanceMensuelleSousLigneCriteria;
import com.growup.comptadecision.service.dto.QuittanceMensuelleSousLigneDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleSousLigneMapper;
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
 * Service for executing complex queries for QuittanceMensuelleSousLigne entities in the database.
 * The main input is a {@link QuittanceMensuelleSousLigneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuittanceMensuelleSousLigneDTO} or a {@link Page} of {@link QuittanceMensuelleSousLigneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuittanceMensuelleSousLigneQueryService extends QueryService<QuittanceMensuelleSousLigne> {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleSousLigneQueryService.class);

    private final QuittanceMensuelleSousLigneRepository quittanceMensuelleSousLigneRepository;

    private final QuittanceMensuelleSousLigneMapper quittanceMensuelleSousLigneMapper;

    public QuittanceMensuelleSousLigneQueryService(QuittanceMensuelleSousLigneRepository quittanceMensuelleSousLigneRepository, QuittanceMensuelleSousLigneMapper quittanceMensuelleSousLigneMapper) {
        this.quittanceMensuelleSousLigneRepository = quittanceMensuelleSousLigneRepository;
        this.quittanceMensuelleSousLigneMapper = quittanceMensuelleSousLigneMapper;
    }

    /**
     * Return a {@link List} of {@link QuittanceMensuelleSousLigneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuittanceMensuelleSousLigneDTO> findByCriteria(QuittanceMensuelleSousLigneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QuittanceMensuelleSousLigne> specification = createSpecification(criteria);
        return quittanceMensuelleSousLigneMapper.toDto(quittanceMensuelleSousLigneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuittanceMensuelleSousLigneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuittanceMensuelleSousLigneDTO> findByCriteria(QuittanceMensuelleSousLigneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QuittanceMensuelleSousLigne> specification = createSpecification(criteria);
        return quittanceMensuelleSousLigneRepository.findAll(specification, page)
            .map(quittanceMensuelleSousLigneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QuittanceMensuelleSousLigneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QuittanceMensuelleSousLigne> specification = createSpecification(criteria);
        return quittanceMensuelleSousLigneRepository.count(specification);
    }

    /**
     * Function to convert QuittanceMensuelleSousLigneCriteria to a {@link Specification}
     */
    private Specification<QuittanceMensuelleSousLigne> createSpecification(QuittanceMensuelleSousLigneCriteria criteria) {
        Specification<QuittanceMensuelleSousLigne> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuittanceMensuelleSousLigne_.id));
            }
            if (criteria.getMontantBase() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantBase(), QuittanceMensuelleSousLigne_.montantBase));
            }
            if (criteria.getMontantTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantTotal(), QuittanceMensuelleSousLigne_.montantTotal));
            }
            if (criteria.getImpotMensuelLigneId() != null) {
                specification = specification.and(buildSpecification(criteria.getImpotMensuelLigneId(),
                    root -> root.join(QuittanceMensuelleSousLigne_.impotMensuelLigne, JoinType.LEFT).get(ImpotMensuelLigne_.id)));
            }
            if (criteria.getQuittanceMensuelleLigneId() != null) {
                specification = specification.and(buildSpecification(criteria.getQuittanceMensuelleLigneId(),
                    root -> root.join(QuittanceMensuelleSousLigne_.quittanceMensuelleLigne, JoinType.LEFT).get(QuittanceMensuelleLigne_.id)));
            }
        }
        return specification;
    }
}
