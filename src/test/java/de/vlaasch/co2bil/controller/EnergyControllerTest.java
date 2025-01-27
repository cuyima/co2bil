package de.vlaasch.co2bil.controller;

import static de.vlaasch.co2bil.testutil.EnergySourcesFixture.co2BalanceFixture;
import static de.vlaasch.co2bil.testutil.EnergySourcesFixture.energyUsageEntriesFixture;
import static de.vlaasch.co2bil.testutil.JsonUtil.asJsonString;
import static de.vlaasch.co2bil.testutil.JsonUtil.fromJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.vlaasch.co2bil.service.emission.Co2BalanceService;
import de.vlaasch.co2bil.service.emission.EnergySourcesFetcher;
import de.vlaasch.co2bil.web.api.Co2Balance;
import de.vlaasch.co2bil.web.api.EnergyUsageRequest;
import de.vlaasch.co2bil.web.controller.EnergyController;

@ExtendWith(MockitoExtension.class)
public class EnergyControllerTest implements WithAssertions {

    @Mock
    private Co2BalanceService co2BalanceService;
    @Mock
    private EnergySourcesFetcher energySourcesFetcher;

    @InjectMocks
    private EnergyController underTest;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @Nested
    @DisplayName("Testing POST /api/v1/energy/balance")
    class CalculateCo2BalanceTests {

        private final EnergyUsageRequest request = new EnergyUsageRequest(energyUsageEntriesFixture());

        @Test
        @DisplayName("should return 200 OK")
        void should_return_200_ok() throws Exception {
            // when
            mockMvc.perform(post("/api/v1/energy/balance")
                    .contentType(APPLICATION_JSON)
                    .content(asJsonString(request)))
                    .andExpect(status().isOk());

        }

        @Test
        @DisplayName("should call service")
        void should_call_service() throws Exception {
            // when
            mockMvc.perform(post("/api/v1/energy/balance")
                    .contentType(APPLICATION_JSON)
                    .content(asJsonString(request)));

            // then
            verify(co2BalanceService).getCo2Balance(request);
        }

        @Test
        @DisplayName("should return response from service")
        void should_return_response_from_service() throws Exception {
            // given
            when(co2BalanceService.getCo2Balance(any())).thenReturn(co2BalanceFixture());

            // when
            MvcResult response = mockMvc.perform(post("/api/v1/energy/balance")
                    .contentType(APPLICATION_JSON)
                    .content(asJsonString(request)))
                    .andReturn();

            // then
            @SuppressWarnings("unchecked")
            List<Co2Balance> result = fromJsonString(response.getResponse().getContentAsString(), List.class);
            assertThat(result).isEqualTo(co2BalanceFixture());
        }
    }
}
