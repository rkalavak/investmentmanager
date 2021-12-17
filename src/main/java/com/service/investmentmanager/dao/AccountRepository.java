package com.service.investmentmanager.dao;

import com.service.investmentmanager.entity.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountData, Long> {
}
