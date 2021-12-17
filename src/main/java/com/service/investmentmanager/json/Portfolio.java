package com.service.investmentmanager.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Portfolio {

    private String stockName;
    private int stockQuantity;
    private long purchasePrice;
    private long stockPrice;
    private double stockValue;
}
