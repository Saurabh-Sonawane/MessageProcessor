package com.mg.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * ProductWiseSale class to maintain for product type wise sale and adjustment
 */
public class ProductWiseSale {
    private String productType;
    private BigDecimal totalAmount;
    private Long totalQuantity;
    private List<AdjustmentDetails> adjustments;

    public ProductWiseSale() {
    }

    public ProductWiseSale(String productType, BigDecimal totalAmount, Long totalQuantity, List<AdjustmentDetails> adjustments) {
        this.productType = productType;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
        this.adjustments = adjustments;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<AdjustmentDetails> getAdjustments() {
        return adjustments;
    }

    public void setAdjustments(List<AdjustmentDetails> adjustments) {
        this.adjustments = adjustments;
    }
}
