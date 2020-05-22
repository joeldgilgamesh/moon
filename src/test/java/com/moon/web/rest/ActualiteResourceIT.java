package com.moon.web.rest;

import com.moon.MoonApp;
import com.moon.domain.Actualite;
import com.moon.repository.ActualiteRepository;
import com.moon.service.ActualiteService;
import com.moon.service.dto.ActualiteDTO;
import com.moon.service.mapper.ActualiteMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ActualiteResource} REST controller.
 */
@SpringBootTest(classes = MoonApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ActualiteResourceIT {

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_PUBLICATION = "AAAAAAAAAA";
    private static final String UPDATED_DATE_PUBLICATION = "BBBBBBBBBB";

    @Autowired
    private ActualiteRepository actualiteRepository;

    @Autowired
    private ActualiteMapper actualiteMapper;

    @Autowired
    private ActualiteService actualiteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restActualiteMockMvc;

    private Actualite actualite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Actualite createEntity(EntityManager em) {
        Actualite actualite = new Actualite()
            .titre(DEFAULT_TITRE)
            .description(DEFAULT_DESCRIPTION)
            .datePublication(DEFAULT_DATE_PUBLICATION);
        return actualite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Actualite createUpdatedEntity(EntityManager em) {
        Actualite actualite = new Actualite()
            .titre(UPDATED_TITRE)
            .description(UPDATED_DESCRIPTION)
            .datePublication(UPDATED_DATE_PUBLICATION);
        return actualite;
    }

    @BeforeEach
    public void initTest() {
        actualite = createEntity(em);
    }

    @Test
    @Transactional
    public void createActualite() throws Exception {
        int databaseSizeBeforeCreate = actualiteRepository.findAll().size();
        // Create the Actualite
        ActualiteDTO actualiteDTO = actualiteMapper.toDto(actualite);
        restActualiteMockMvc.perform(post("/api/actualites").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actualiteDTO)))
            .andExpect(status().isCreated());

        // Validate the Actualite in the database
        List<Actualite> actualiteList = actualiteRepository.findAll();
        assertThat(actualiteList).hasSize(databaseSizeBeforeCreate + 1);
        Actualite testActualite = actualiteList.get(actualiteList.size() - 1);
        assertThat(testActualite.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testActualite.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testActualite.getDatePublication()).isEqualTo(DEFAULT_DATE_PUBLICATION);
    }

    @Test
    @Transactional
    public void createActualiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = actualiteRepository.findAll().size();

        // Create the Actualite with an existing ID
        actualite.setId(1L);
        ActualiteDTO actualiteDTO = actualiteMapper.toDto(actualite);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActualiteMockMvc.perform(post("/api/actualites").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actualiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actualite in the database
        List<Actualite> actualiteList = actualiteRepository.findAll();
        assertThat(actualiteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllActualites() throws Exception {
        // Initialize the database
        actualiteRepository.saveAndFlush(actualite);

        // Get all the actualiteList
        restActualiteMockMvc.perform(get("/api/actualites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(actualite.getId().intValue())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].datePublication").value(hasItem(DEFAULT_DATE_PUBLICATION)));
    }
    
    @Test
    @Transactional
    public void getActualite() throws Exception {
        // Initialize the database
        actualiteRepository.saveAndFlush(actualite);

        // Get the actualite
        restActualiteMockMvc.perform(get("/api/actualites/{id}", actualite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(actualite.getId().intValue()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.datePublication").value(DEFAULT_DATE_PUBLICATION));
    }
    @Test
    @Transactional
    public void getNonExistingActualite() throws Exception {
        // Get the actualite
        restActualiteMockMvc.perform(get("/api/actualites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActualite() throws Exception {
        // Initialize the database
        actualiteRepository.saveAndFlush(actualite);

        int databaseSizeBeforeUpdate = actualiteRepository.findAll().size();

        // Update the actualite
        Actualite updatedActualite = actualiteRepository.findById(actualite.getId()).get();
        // Disconnect from session so that the updates on updatedActualite are not directly saved in db
        em.detach(updatedActualite);
        updatedActualite
            .titre(UPDATED_TITRE)
            .description(UPDATED_DESCRIPTION)
            .datePublication(UPDATED_DATE_PUBLICATION);
        ActualiteDTO actualiteDTO = actualiteMapper.toDto(updatedActualite);

        restActualiteMockMvc.perform(put("/api/actualites").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actualiteDTO)))
            .andExpect(status().isOk());

        // Validate the Actualite in the database
        List<Actualite> actualiteList = actualiteRepository.findAll();
        assertThat(actualiteList).hasSize(databaseSizeBeforeUpdate);
        Actualite testActualite = actualiteList.get(actualiteList.size() - 1);
        assertThat(testActualite.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testActualite.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testActualite.getDatePublication()).isEqualTo(UPDATED_DATE_PUBLICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingActualite() throws Exception {
        int databaseSizeBeforeUpdate = actualiteRepository.findAll().size();

        // Create the Actualite
        ActualiteDTO actualiteDTO = actualiteMapper.toDto(actualite);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActualiteMockMvc.perform(put("/api/actualites").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actualiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actualite in the database
        List<Actualite> actualiteList = actualiteRepository.findAll();
        assertThat(actualiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActualite() throws Exception {
        // Initialize the database
        actualiteRepository.saveAndFlush(actualite);

        int databaseSizeBeforeDelete = actualiteRepository.findAll().size();

        // Delete the actualite
        restActualiteMockMvc.perform(delete("/api/actualites/{id}", actualite.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Actualite> actualiteList = actualiteRepository.findAll();
        assertThat(actualiteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
