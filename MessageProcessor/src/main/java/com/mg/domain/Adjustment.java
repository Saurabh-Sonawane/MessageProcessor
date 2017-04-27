package com.mg.domain;

import java.math.BigDecimal;

/**
 * Adjustment entity
 */
public class Adjustment {
    private Operation operation;
    private String productType;
    private BigDecimal amount;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
