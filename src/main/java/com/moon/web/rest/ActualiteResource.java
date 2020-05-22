package com.moon.web.rest;

import com.moon.service.ActualiteService;
import com.moon.web.rest.errors.BadRequestAlertException;
import com.moon.service.dto.ActualiteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.moon.domain.Actualite}.
 */
@RestController
@RequestMapping("/api")
public class ActualiteResource {

    private final Logger log = LoggerFactory.getLogger(ActualiteResource.class);

    private static final String ENTITY_NAME = "actualite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActualiteService actualiteService;

    public ActualiteResource(ActualiteService actualiteService) {
        this.actualiteService = actualiteService;
    }

    /**
     * {@code POST  /actualites} : Create a new actualite.
     *
     * @param actualiteDTO the actualiteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new actualiteDTO, or with status {@code 400 (Bad Request)} if the actualite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/actualites")
    public ResponseEntity<ActualiteDTO> createActualite(@RequestBody ActualiteDTO actualiteDTO) throws URISyntaxException {
        log.debug("REST request to save Actualite : {}", actualiteDTO);
        if (actualiteDTO.getId() != null) {
            throw new BadRequestAlertException("A new actualite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActualiteDTO result = actualiteService.save(actualiteDTO);
        return ResponseEntity.created(new URI("/api/actualites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /actualites} : Updates an existing actualite.
     *
     * @param actualiteDTO the actualiteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated actualiteDTO,
     * or with status {@code 400 (Bad Request)} if the actualiteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the actualiteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/actualites")
    public ResponseEntity<ActualiteDTO> updateActualite(@RequestBody ActualiteDTO actualiteDTO) throws URISyntaxException {
        log.debug("REST request to update Actualite : {}", actualiteDTO);
        if (actualiteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActualiteDTO result = actualiteService.save(actualiteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, actualiteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /actualites} : get all the actualites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of actualites in body.
     */
    @GetMapping("/actualites")
    public ResponseEntity<List<ActualiteDTO>> getAllActualites(Pageable pageable) {
        log.debug("REST request to get a page of Actualites");
        Page<ActualiteDTO> page = actualiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /actualites/:id} : get the "id" actualite.
     *
     * @param id the id of the actualiteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the actualiteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/actualites/{id}")
    public ResponseEntity<ActualiteDTO> getActualite(@PathVariable Long id) {
        log.debug("REST request to get Actualite : {}", id);
        Optional<ActualiteDTO> actualiteDTO = actualiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(actualiteDTO);
    }

    /**
     * {@code DELETE  /actualites/:id} : delete the "id" actualite.
     *
     * @param id the id of the actualiteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/actualites/{id}")
    public ResponseEntity<Void> deleteActualite(@PathVariable Long id) {
        log.debug("REST request to delete Actualite : {}", id);

        actualiteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
