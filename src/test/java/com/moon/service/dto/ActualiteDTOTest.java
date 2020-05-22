package com.moon.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.moon.web.rest.TestUtil;

public class ActualiteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActualiteDTO.class);
        ActualiteDTO actualiteDTO1 = new ActualiteDTO();
        actualiteDTO1.setId(1L);
        ActualiteDTO actualiteDTO2 = new ActualiteDTO();
        assertThat(actualiteDTO1).isNotEqualTo(actualiteDTO2);
        actualiteDTO2.setId(actualiteDTO1.getId());
        assertThat(actualiteDTO1).isEqualTo(actualiteDTO2);
        actualiteDTO2.setId(2L);
        assertThat(actualiteDTO1).isNotEqualTo(actualiteDTO2);
        actualiteDTO1.setId(null);
        assertThat(actualiteDTO1).isNotEqualTo(actualiteDTO2);
    }
}
