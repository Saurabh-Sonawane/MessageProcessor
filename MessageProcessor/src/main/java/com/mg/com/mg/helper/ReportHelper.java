package com.mg.com.mg.helper;

import com.mg.domain.AdjustmentDetails;
import com.mg.domain.ProductWiseSale;

import java.util.Map;

/**
 * ReportHelper class to print the reporting details on console
 */
public class ReportHelper {

    public static void printProductTypeWiseSale(Map<String, ProductWiseSale> productWiseSaleMap) {
        System.out.println("\n Product_Type\t Total_Quantity \t Total_Amount");
        for (Map.Entry<String,ProductWiseSale> entry : productWiseSaleMap.entrySet()) {
            String key = entry.getKey();
            ProductWiseSale value = entry.getValue();
            System.out.println("\n "+value.getProductType()+"\t "+value.getTotalQuantity()+" \t "+value.getTotalAmount());
        }
    }

    public static void printProductTypeWiseSaleWithAdjustment(Map<String, ProductWiseSale> productWiseSaleMap) {
        System.out.println("\n Product_Type\t Operation \t Adjustment_Amount_For_Each_Sale \t Adjusted_Amount\t Total_Sale_Amount");
        for (Map.Entry<String,ProductWiseSale> entry : productWiseSaleMap.entrySet()) {
            String key = entry.getKey();
            ProductWiseSale value = entry.getValue();
            for(AdjustmentDetails details : value.getAdjustments()) {
                System.out.println("\n " + value.getProductType() + "\t " + details.getOperation() + " \t " + details.getAmount()+ " \t " + details.getTotalAdjustedAmount()+ " \t " + details.getTotalSaleAmount());
            }
        }
    }
}
