package com.service.investmentmanager.dao;

import com.service.investmentmanager.entity.AccountData;
import com.service.investmentmanager.entity.PortfolioData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioData, Long> {
    List<PortfolioData> getPortfolioByAccountData(AccountData accountData);
}
