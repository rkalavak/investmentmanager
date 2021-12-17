package com.service.investmentmanager.service;

import com.service.investmentmanager.dao.AccountRepository;
import com.service.investmentmanager.dao.PortfolioRepository;
import com.service.investmentmanager.entity.AccountData;
import com.service.investmentmanager.entity.PortfolioData;
import com.service.investmentmanager.json.Portfolio;
import com.service.investmentmanager.json.PortfolioResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    @InjectMocks
    private PortfolioService portfolioService;

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void getPortfolioByAccountId() {

        ReflectionTestUtils.setField(portfolioService, "stockPrice", 300);

        Optional<AccountData> accountData = Optional.of(AccountData.builder().accountId(1l).balance(300d).build());
        Mockito.when(accountRepository.findById(1l)).thenReturn(accountData);
        List<PortfolioData> portfolioDataList = new ArrayList<>();
        PortfolioData apple = PortfolioData.builder().orderId(1l).stockName("Apple").stockQuantity(10).purchasePrice(300l).build();
        portfolioDataList.add(apple);
        Mockito.when(portfolioRepository.getPortfolioByAccountData(Mockito.any())).thenReturn(portfolioDataList);

        List<Portfolio> portfolioDetails = new ArrayList<>();
        Portfolio portfolio = Portfolio.builder().stockName("Apple").stockQuantity(10).purchasePrice(300).stockPrice(300).stockValue(2000.0).build();
        portfolioDetails.add(portfolio);
        PortfolioResponse expected = PortfolioResponse.builder().totalPortfolioValue(2000.0).accountBalance(300.0).portfolioDetails(portfolioDetails).build();

        PortfolioResponse actual = portfolioService.getPortfolioByAccountId(1l);

        Assertions.assertEquals(expected, actual);
    }
}