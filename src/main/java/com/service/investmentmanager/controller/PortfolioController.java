package com.service.investmentmanager.controller;

import com.service.investmentmanager.json.PortfolioResponse;
import com.service.investmentmanager.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/customers")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/accounts/{accountId}/portfolio")
    public ResponseEntity<PortfolioResponse> getPortfolioForAccount(@PathVariable(name = "accountId") @NotNull long accountId) {
        PortfolioResponse portfolioResponse = portfolioService.getPortfolioByAccountId(accountId);
        return ResponseEntity.ok(portfolioResponse);
    }
}
