package com.service.investmentmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PORTFOLIO")
public class PortfolioData {

    @Id
    @Column(name = "ORDER_ID")
    @SequenceGenerator(name = "portfolio_sequence", sequenceName = "portfolio_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portfolio_sequence")
    private Long orderId;
    @Column(name = "STOCK_NAME")
    private String stockName;
    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;
    @Column(name = "PURCHASE_PRICE")
    private Long purchasePrice;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountData accountData;

    public double getStockValue() {
        return stockQuantity * 200;
    }
}
