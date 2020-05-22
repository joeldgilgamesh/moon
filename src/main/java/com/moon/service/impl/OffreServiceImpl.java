package com.moon.service.impl;

import com.moon.service.OffreService;
import com.moon.domain.Offre;
import com.moon.repository.OffreRepository;
import com.moon.service.dto.OffreDTO;
import com.moon.service.mapper.OffreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Offre}.
 */
@Service
@Transactional
public class OffreServiceImpl implements OffreService {

    private final Logger log = LoggerFactory.getLogger(OffreServiceImpl.class);

    private final OffreRepository offreRepository;

    private final OffreMapper offreMapper;

    public OffreServiceImpl(OffreRepository offreRepository, OffreMapper offreMapper) {
        this.offreRepository = offreRepository;
        this.offreMapper = offreMapper;
    }

    /**
     * Save a offre.
     *
     * @param offreDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OffreDTO save(OffreDTO offreDTO) {
        log.debug("Request to save Offre : {}", offreDTO);
        Offre offre = offreMapper.toEntity(offreDTO);
        offre = offreRepository.save(offre);
        return offreMapper.toDto(offre);
    }

    /**
     * Get all the offres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OffreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Offres");
        return offreRepository.findAll(pageable)
            .map(offreMapper::toDto);
    }


    /**
     * Get one offre by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OffreDTO> findOne(Long id) {
        log.debug("Request to get Offre : {}", id);
        return offreRepository.findById(id)
            .map(offreMapper::toDto);
    }

    /**
     * Delete the offre by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Offre : {}", id);

        offreRepository.deleteById(id);
    }
}
