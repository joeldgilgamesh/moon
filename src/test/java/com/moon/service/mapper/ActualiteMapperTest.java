package com.moon.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ActualiteMapperTest {

    private ActualiteMapper actualiteMapper;

    @BeforeEach
    public void setUp() {
        actualiteMapper = new ActualiteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(actualiteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(actualiteMapper.fromId(null)).isNull();
    }
}
