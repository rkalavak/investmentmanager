package com.service.investmentmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class AccountData {

    @Id
    @Column(name = "ACCOUNT_ID")
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private Long accountId;
    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;
    @Column(name = "ACCOUNT_BALANCE")
    private Double balance;
    @OneToMany(mappedBy = "accountData")
    private List<PortfolioData> portfolioData;
}
