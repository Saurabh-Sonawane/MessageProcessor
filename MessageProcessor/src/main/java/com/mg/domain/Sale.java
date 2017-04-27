package com.mg.domain;

import java.math.BigDecimal;

/**
 * Sale to define sale
 */
public class Sale {
    Integer quantity;
    String productType;
    BigDecimal pricePerItem;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(BigDecimal pricePerItem) {
        this.pricePerItem = pricePerItem;
    }
}
