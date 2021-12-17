package com.service.investmentmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.investmentmanager.InvestmentManagerApplication;
import com.service.investmentmanager.json.Portfolio;
import com.service.investmentmanager.json.PortfolioResponse;
import com.service.investmentmanager.service.PortfolioService;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InvestmentManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PortfolioControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private PortfolioService portfolioService;

    @BeforeAll
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(username = "renukalavakuri", roles = {"ADMIN"})
    void getPortfolioForAccount() throws Exception {

        List<Portfolio> portfolioDetails = new ArrayList<>();
        Portfolio portfolio = Portfolio.builder().stockName("Apple").stockQuantity(10).purchasePrice(500).stockPrice(300).stockValue(2000.0).build();
        portfolioDetails.add(portfolio);
        PortfolioResponse portfolioResponse = PortfolioResponse.builder().totalPortfolioValue(20000.0).accountBalance(500000.0).portfolioDetails(portfolioDetails).build();

        Mockito.when(portfolioService.getPortfolioByAccountId(1l)).thenReturn(portfolioResponse);

        String response = mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:" + port + "/api/customers/accounts/1/portfolio")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonAssert.assertJsonEquals(getJsonString(portfolioResponse), response);
    }

    private String getJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}