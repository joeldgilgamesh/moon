package com.moon.service.impl;

import com.moon.service.ActualiteService;
import com.moon.domain.Actualite;
import com.moon.repository.ActualiteRepository;
import com.moon.service.dto.ActualiteDTO;
import com.moon.service.mapper.ActualiteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Actualite}.
 */
@Service
@Transactional
public class ActualiteServiceImpl implements ActualiteService {

    private final Logger log = LoggerFactory.getLogger(ActualiteServiceImpl.class);

    private final ActualiteRepository actualiteRepository;

    private final ActualiteMapper actualiteMapper;

    public ActualiteServiceImpl(ActualiteRepository actualiteRepository, ActualiteMapper actualiteMapper) {
        this.actualiteRepository = actualiteRepository;
        this.actualiteMapper = actualiteMapper;
    }

    /**
     * Save a actualite.
     *
     * @param actualiteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ActualiteDTO save(ActualiteDTO actualiteDTO) {
        log.debug("Request to save Actualite : {}", actualiteDTO);
        Actualite actualite = actualiteMapper.toEntity(actualiteDTO);
        actualite = actualiteRepository.save(actualite);
        return actualiteMapper.toDto(actualite);
    }

    /**
     * Get all the actualites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActualiteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actualites");
        return actualiteRepository.findAll(pageable)
            .map(actualiteMapper::toDto);
    }


    /**
     * Get one actualite by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ActualiteDTO> findOne(Long id) {
        log.debug("Request to get Actualite : {}", id);
        return actualiteRepository.findById(id)
            .map(actualiteMapper::toDto);
    }

    /**
     * Delete the actualite by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Actualite : {}", id);

        actualiteRepository.deleteById(id);
    }
}
