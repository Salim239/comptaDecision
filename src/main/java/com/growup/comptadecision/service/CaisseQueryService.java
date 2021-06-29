package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Caisse;
import com.growup.comptadecision.domain.Caisse_;
import com.growup.comptadecision.domain.FicheClient_;
import com.growup.comptadecision.domain.LigneCaisse_;
import com.growup.comptadecision.repository.CaisseRepository;
import com.growup.comptadecision.service.dto.CaisseCriteria;
import com.growup.comptadecision.service.dto.CaisseDTO;
import com.growup.comptadecision.service.mapper.CaisseMapper;
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
 * Service for executing complex queries for Caisse entities in the database.
 * The main input is a {@link CaisseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaisseDTO} or a {@link Page} of {@link CaisseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaisseQueryService extends QueryService<Caisse> {

    private final Logger log = LoggerFactory.getLogger(CaisseQueryService.class);

    private final CaisseRepository caisseRepository;

    private final CaisseMapper caisseMapper;

    public CaisseQueryService(CaisseRepository caisseRepository, CaisseMapper caisseMapper) {
        this.caisseRepository = caisseRepository;
        this.caisseMapper = caisseMapper;
    }

    /**
     * Return a {@link List} of {@link CaisseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaisseDTO> findByCriteria(CaisseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Caisse> specification = createSpecification(criteria);
        return caisseMapper.toDto(caisseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaisseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaisseDTO> findByCriteria(CaisseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Caisse> specification = createSpecification(criteria);
        return caisseRepository.findAll(specification, page)
            .map(caisseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaisseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Caisse> specification = createSpecification(criteria);
        return caisseRepository.count(specification);
    }

    /**
     * Function to convert CaisseCriteria to a {@link Specification}
     */
    private Specification<Caisse> createSpecification(CaisseCriteria criteria) {
        Specification<Caisse> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Caisse_.id));
            }
            if (criteria.getMontantTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantTotal(), Caisse_.montantTotal));
            }
            if (criteria.getMontantReport() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantReport(), Caisse_.montantReport));
            }
            if (criteria.getCloturee() != null) {
                specification = specification.and(buildSpecification(criteria.getCloturee(), Caisse_.cloturee));
            }
            if (criteria.getLigneCaisseId() != null) {
                specification = specification.and(buildSpecification(criteria.getLigneCaisseId(),
                    root -> root.join(Caisse_.ligneCaisses, JoinType.LEFT).get(LigneCaisse_.id)));
            }
            if (criteria.getFicheClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientId(),
                    root -> root.join(Caisse_.ficheClient, JoinType.LEFT).get(FicheClient_.id)));
            }
        }
        return specification;
    }
}
