package com.service.investmentmanager.service;

import com.service.investmentmanager.dao.AccountRepository;
import com.service.investmentmanager.dao.PortfolioRepository;
import com.service.investmentmanager.entity.AccountData;
import com.service.investmentmanager.entity.PortfolioData;
import com.service.investmentmanager.exception.AccountNumberNotFoundException;
import com.service.investmentmanager.json.Portfolio;
import com.service.investmentmanager.json.PortfolioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Value("${stockPrice}")
    private long stockPrice;

    public PortfolioResponse getPortfolioByAccountId(long accoundId) {

        AccountData accountDetails = accountRepository
                .findById(accoundId).map(accountData -> accountData)
                .orElseThrow(() -> new AccountNumberNotFoundException(String.format("Account details for accountId: %s not found", accoundId)));

        AccountData accountData = AccountData.builder().accountId(accoundId).build();
        return Optional.ofNullable(portfolioRepository.getPortfolioByAccountData(accountData))
                .map(portfolios -> getPortfolioResponse(portfolios, accountDetails))
                .orElseThrow(() -> new RuntimeException("Something went wrong while retrieving portfolioo details"));
    }

    private PortfolioResponse getPortfolioResponse(List<PortfolioData> portfolioByAccount, AccountData accountDetails) {

        double totalPortfolioValue = portfolioByAccount.stream().mapToDouble(PortfolioData::getStockValue).sum();
        List<Portfolio> portfolio = portfolioByAccount.stream().map(portfolioData -> Portfolio
                .builder()
                .stockName(portfolioData.getStockName())
                .stockQuantity(portfolioData.getStockQuantity())
                .purchasePrice(portfolioData.getPurchasePrice()).stockPrice(stockPrice).stockValue(portfolioData.getStockValue()).build()).collect(Collectors.toList());

        PortfolioResponse portfolioResponse = PortfolioResponse
                .builder()
                .totalPortfolioValue(totalPortfolioValue)
                .accountBalance(accountDetails.getBalance())
                .portfolioDetails(portfolio)
                .build();

        return portfolioResponse;
    }
}
