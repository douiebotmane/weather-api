package com.weather.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void currentWeather_validCity_OK() throws Exception {
        mockMvc.perform(get("/weather/current")
                        .param("location", "Toulouse")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.temperature").exists())
                .andExpect(jsonPath("$.windSpeed").exists())
                .andExpect(jsonPath("$.humidity").exists());
    }

    @Test
    void currentWeather_invalidCity_KO() throws Exception {
        mockMvc.perform(get("/weather/current")
                        .param("location", "skdjfksbjfb")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void forecastWeather_validCity_OK() throws Exception {
        mockMvc.perform(get("/weather/forecast")
                        .param("location", "Toulouse")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.evolution").exists())
                .andExpect(jsonPath("$.tempTrend").exists())
                .andExpect(jsonPath("$.pressureTrend").exists())
                .andExpect(jsonPath("$.windCategory").exists());
    }

    @Test
    void forecastWeather_invalidCity_KO() throws Exception {
        mockMvc.perform(get("/weather/forecast")
                        .param("location", "skdjfksbjfb")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable());
    }
}