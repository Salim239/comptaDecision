package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Cnss;
import com.growup.comptadecision.domain.Cnss_;
import com.growup.comptadecision.domain.FicheClient_;
import com.growup.comptadecision.repository.CnssRepository;
import com.growup.comptadecision.service.dto.CnssCriteria;
import com.growup.comptadecision.service.dto.CnssDTO;
import com.growup.comptadecision.service.mapper.CnssMapper;
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
 * Service for executing complex queries for Cnss entities in the database.
 * The main input is a {@link CnssCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CnssDTO} or a {@link Page} of {@link CnssDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CnssQueryService extends QueryService<Cnss> {

    private final Logger log = LoggerFactory.getLogger(CnssQueryService.class);

    private final CnssRepository cnssRepository;

    private final CnssMapper cnssMapper;

    public CnssQueryService(CnssRepository cnssRepository, CnssMapper cnssMapper) {
        this.cnssRepository = cnssRepository;
        this.cnssMapper = cnssMapper;
    }

    /**
     * Return a {@link List} of {@link CnssDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CnssDTO> findByCriteria(CnssCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cnss> specification = createSpecification(criteria);
        return cnssMapper.toDto(cnssRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CnssDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CnssDTO> findByCriteria(CnssCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cnss> specification = createSpecification(criteria);
        return cnssRepository.findAll(specification, page)
            .map(cnssMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CnssCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cnss> specification = createSpecification(criteria);
        return cnssRepository.count(specification);
    }

    /**
     * Function to convert CnssCriteria to a {@link Specification}
     */
    private Specification<Cnss> createSpecification(CnssCriteria criteria) {
        Specification<Cnss> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Cnss_.id));
            }
            if (criteria.getAnnee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnnee(), Cnss_.annee));
            }
            if (criteria.getTrimestre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrimestre(), Cnss_.trimestre));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Cnss_.date));
            }
            if (criteria.getNumeroQuittance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroQuittance(), Cnss_.numeroQuittance));
            }
            if (criteria.getMontantSalaireBrutNormal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantSalaireBrutNormal(), Cnss_.montantSalaireBrutNormal));
            }
            if (criteria.getMontantSalaireBrutKarama() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantSalaireBrutKarama(), Cnss_.montantSalaireBrutKarama));
            }
            if (criteria.getTypeCnss() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeCnss(), Cnss_.typeCnss));
            }
            if (criteria.getTypeDeclaration() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeDeclaration(), Cnss_.typeDeclaration));
            }
            if (criteria.getTauxCnssNormal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxCnssNormal(), Cnss_.tauxCnssNormal));
            }
            if (criteria.getTauxCnssKarama() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxCnssKarama(), Cnss_.tauxCnssKarama));
            }
            if (criteria.getTauxCnssNormalAccident() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxCnssNormalAccident(), Cnss_.tauxCnssNormalAccident));
            }
            if (criteria.getTauxCnssKaramaAccident() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxCnssKaramaAccident(), Cnss_.tauxCnssKaramaAccident));
            }
            if (criteria.getMontantCnssNormal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantCnssNormal(), Cnss_.montantCnssNormal));
            }
            if (criteria.getMontantCnssKarama() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantCnssKarama(), Cnss_.montantCnssKarama));
            }
            if (criteria.getMontantTotalCnss() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantTotalCnss(), Cnss_.montantTotalCnss));
            }
            if (criteria.getMontantTotalSalaireBrut() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantTotalSalaireBrut(), Cnss_.montantTotalSalaireBrut));
            }
            if (criteria.getFicheClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientId(),
                    root -> root.join(Cnss_.ficheClient, JoinType.LEFT).get(FicheClient_.id)));
            }
        }
        return specification;
    }
}
