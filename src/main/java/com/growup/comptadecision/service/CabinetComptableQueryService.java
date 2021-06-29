package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.CabinetComptable;
import com.growup.comptadecision.domain.CabinetComptable_;
import com.growup.comptadecision.domain.FicheClient_;
import com.growup.comptadecision.repository.CabinetComptableRepository;
import com.growup.comptadecision.service.dto.CabinetComptableCriteria;
import com.growup.comptadecision.service.dto.CabinetComptableDTO;
import com.growup.comptadecision.service.mapper.CabinetComptableMapper;
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
 * Service for executing complex queries for CabinetComptable entities in the database.
 * The main input is a {@link CabinetComptableCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CabinetComptableDTO} or a {@link Page} of {@link CabinetComptableDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CabinetComptableQueryService extends QueryService<CabinetComptable> {

    private final Logger log = LoggerFactory.getLogger(CabinetComptableQueryService.class);

    private final CabinetComptableRepository cabinetComptableRepository;

    private final CabinetComptableMapper cabinetComptableMapper;

    public CabinetComptableQueryService(CabinetComptableRepository cabinetComptableRepository, CabinetComptableMapper cabinetComptableMapper) {
        this.cabinetComptableRepository = cabinetComptableRepository;
        this.cabinetComptableMapper = cabinetComptableMapper;
    }

    /**
     * Return a {@link List} of {@link CabinetComptableDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CabinetComptableDTO> findByCriteria(CabinetComptableCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CabinetComptable> specification = createSpecification(criteria);
        return cabinetComptableMapper.toDto(cabinetComptableRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CabinetComptableDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CabinetComptableDTO> findByCriteria(CabinetComptableCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CabinetComptable> specification = createSpecification(criteria);
        return cabinetComptableRepository.findAll(specification, page)
            .map(cabinetComptableMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CabinetComptableCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CabinetComptable> specification = createSpecification(criteria);
        return cabinetComptableRepository.count(specification);
    }

    /**
     * Function to convert CabinetComptableCriteria to a {@link Specification}
     */
    private Specification<CabinetComptable> createSpecification(CabinetComptableCriteria criteria) {
        Specification<CabinetComptable> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CabinetComptable_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CabinetComptable_.code));
            }
            if (criteria.getNombreLicense() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNombreLicense(), CabinetComptable_.nombreLicense));
            }
            if (criteria.getFicheClientCabinetId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientCabinetId(),
                    root -> root.join(CabinetComptable_.ficheClientCabinet, JoinType.LEFT).get(FicheClient_.id)));
            }
            if (criteria.getClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getClientId(),
                    root -> root.join(CabinetComptable_.clients, JoinType.LEFT).get(FicheClient_.id)));
            }
        }
        return specification;
    }
}
