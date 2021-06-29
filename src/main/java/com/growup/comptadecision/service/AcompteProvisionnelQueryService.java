package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.AcompteProvisionnel;
import com.growup.comptadecision.domain.AcompteProvisionnel_;
import com.growup.comptadecision.domain.FicheClient_;
import com.growup.comptadecision.repository.AcompteProvisionnelRepository;
import com.growup.comptadecision.service.dto.AcompteProvisionnelCriteria;
import com.growup.comptadecision.service.dto.AcompteProvisionnelDTO;
import com.growup.comptadecision.service.mapper.AcompteProvisionnelMapper;
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
 * Service for executing complex queries for AcompteProvisionnel entities in the database.
 * The main input is a {@link AcompteProvisionnelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AcompteProvisionnelDTO} or a {@link Page} of {@link AcompteProvisionnelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AcompteProvisionnelQueryService extends QueryService<AcompteProvisionnel> {

    private final Logger log = LoggerFactory.getLogger(AcompteProvisionnelQueryService.class);

    private final AcompteProvisionnelRepository acompteProvisionnelRepository;

    private final AcompteProvisionnelMapper acompteProvisionnelMapper;

    public AcompteProvisionnelQueryService(AcompteProvisionnelRepository acompteProvisionnelRepository, AcompteProvisionnelMapper acompteProvisionnelMapper) {
        this.acompteProvisionnelRepository = acompteProvisionnelRepository;
        this.acompteProvisionnelMapper = acompteProvisionnelMapper;
    }

    /**
     * Return a {@link List} of {@link AcompteProvisionnelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AcompteProvisionnelDTO> findByCriteria(AcompteProvisionnelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AcompteProvisionnel> specification = createSpecification(criteria);
        return acompteProvisionnelMapper.toDto(acompteProvisionnelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AcompteProvisionnelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AcompteProvisionnelDTO> findByCriteria(AcompteProvisionnelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AcompteProvisionnel> specification = createSpecification(criteria);
        return acompteProvisionnelRepository.findAll(specification, page)
            .map(acompteProvisionnelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AcompteProvisionnelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AcompteProvisionnel> specification = createSpecification(criteria);
        return acompteProvisionnelRepository.count(specification);
    }

    /**
     * Function to convert AcompteProvisionnelCriteria to a {@link Specification}
     */
    private Specification<AcompteProvisionnel> createSpecification(AcompteProvisionnelCriteria criteria) {
        Specification<AcompteProvisionnel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AcompteProvisionnel_.id));
            }
            if (criteria.getAnnee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnnee(), AcompteProvisionnel_.annee));
            }
            if (criteria.getNumero() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumero(), AcompteProvisionnel_.numero));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), AcompteProvisionnel_.date));
            }
            if (criteria.getNumeroQuittance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroQuittance(), AcompteProvisionnel_.numeroQuittance));
            }
            if (criteria.getMontantBase() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantBase(), AcompteProvisionnel_.montantBase));
            }
            if (criteria.getMontantAcompteProvisionnel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAcompteProvisionnel(), AcompteProvisionnel_.montantAcompteProvisionnel));
            }
            if (criteria.getMontantReportAnterieur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantReportAnterieur(), AcompteProvisionnel_.montantReportAnterieur));
            }
            if (criteria.getMontantRetenueSource() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantRetenueSource(), AcompteProvisionnel_.montantRetenueSource));
            }
            if (criteria.getMontantNet() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantNet(), AcompteProvisionnel_.montantNet));
            }
            if (criteria.getTypeDeclaration() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeDeclaration(), AcompteProvisionnel_.type));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildSpecification(criteria.getStatut(), AcompteProvisionnel_.statut));
            }
            if (criteria.getFicheClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientId(),
                    root -> root.join(AcompteProvisionnel_.ficheClient, JoinType.LEFT).get(FicheClient_.id)));
            }
        }
        return specification;
    }
}
