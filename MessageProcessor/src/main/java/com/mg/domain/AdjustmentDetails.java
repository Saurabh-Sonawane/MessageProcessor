package com.mg.domain;

import java.math.BigDecimal;

/**
 * AdjustmentDetails entity
 */
public class AdjustmentDetails {
    private Operation operation;
    private BigDecimal amount;
    private BigDecimal totalAdjustedAmount;
    private BigDecimal totalSaleAmount;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalAdjustedAmount() {
        return totalAdjustedAmount;
    }

    public void setTotalAdjustedAmount(BigDecimal totalAdjustedAmount) {
        this.totalAdjustedAmount = totalAdjustedAmount;
    }

    public BigDecimal getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(BigDecimal totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }
}
