package com.mg.controller;

import com.mg.com.mg.helper.ReportHelper;
import com.mg.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MessageProcessor class to implement message processing logic
 */
public class MessageProcessor {

    //to record all sale
    private List<Sale> saleRecord = new ArrayList<Sale>();

    //to record all adjustment
    private List<Adjustment> adjustmentList = new ArrayList<Adjustment>();

    //to record product type wise sale and adjustment
    private Map<String, ProductWiseSale> productWiseSaleMap = new HashMap<String, ProductWiseSale>();

    /**
     * processMessage method to implement message processing logic
     * @param message : message details
     */
    public void processMessage(Message message) {

        if(message.getOperation() != null) {
            Adjustment adjustment = new Adjustment();
            adjustment.setOperation(message.getOperation());
            adjustment.setProductType(message.getProductType());
            adjustment.setAmount(message.getAmount());
            adjustmentList.add(adjustment);
            calculateAdjustment(adjustment);

        } else {
            Sale sale = new Sale();
            sale.setQuantity(message.getQuantity());
            sale.setProductType(message.getProductType());
            sale.setPricePerItem(message.getPricePerItem());
            saleRecord.add(sale);
            calculateSale(sale);
        }

        if( (saleRecord.size() + adjustmentList.size()) % 10 == 0) {
            System.out.println("************************************************");
            System.out.println("Product type wise sale for message 0 to "+ (saleRecord.size() + adjustmentList.size()));
            System.out.println("************************************************");
            ReportHelper.printProductTypeWiseSale(productWiseSaleMap);
        }

        if( (saleRecord.size() + adjustmentList.size()) % 50 == 0) {
            System.out.println("\n\n************************************************");
            System.out.println("The application is paused now, will not accept new message");
            System.out.println("Product type wise sale adjustment for message 0 to "+ (saleRecord.size() + adjustmentList.size()));
            System.out.println("************************************************");
            ReportHelper.printProductTypeWiseSaleWithAdjustment(productWiseSaleMap);
            System.exit(0);
        }
    }

    private void calculateSale(Sale sale) {
        if(productWiseSaleMap.containsKey(sale.getProductType())) {

            ProductWiseSale productWiseSale = productWiseSaleMap.get(sale.getProductType());
            BigDecimal totalAmount =  productWiseSale.getTotalAmount().add(sale.getPricePerItem().multiply(new BigDecimal(sale.getQuantity())));
            Long totalQuantity = productWiseSale.getTotalQuantity() + new Long(sale.getQuantity());
            productWiseSale.setTotalAmount(totalAmount);
            productWiseSale.setTotalQuantity(totalQuantity);

        } else {
            BigDecimal totalAmount =  sale.getPricePerItem().multiply(new BigDecimal(sale.getQuantity()));
            ProductWiseSale productWiseSale = new ProductWiseSale(sale.getProductType(),totalAmount, new Long(sale.getQuantity()), new ArrayList<AdjustmentDetails>() );
            productWiseSaleMap.put(sale.getProductType(), productWiseSale);
        }
    }

    private void calculateAdjustment(final Adjustment adjustment) {
        if(productWiseSaleMap.containsKey(adjustment.getProductType())) {

            ProductWiseSale productWiseSale = productWiseSaleMap.get(adjustment.getProductType());
            AdjustmentDetails adjustmentDetails = new AdjustmentDetails();
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal totalAdjustedAmount = new BigDecimal(0);
            long count;

            switch (adjustment.getOperation()) {
                case ADD:
                    count = saleRecord.stream().filter(o -> o.getProductType().equals(adjustment.getProductType())).count();
                    totalAdjustedAmount = adjustment.getAmount().multiply(new BigDecimal(count));
                    totalAmount = productWiseSale.getTotalAmount().add(totalAdjustedAmount);
                    break;
                case MULTIPLY:
                    for (Sale sale : saleRecord) {
                        if(sale.getProductType().equals(adjustment.getProductType())) {
                            totalAdjustedAmount = totalAdjustedAmount.add(adjustment.getAmount().multiply(sale.getPricePerItem().multiply(new BigDecimal(sale.getQuantity()))));
                        }
                    }
                    totalAmount = productWiseSale.getTotalAmount().add(totalAdjustedAmount);
                    break;
                case SUBTRACT:
                    count = saleRecord.stream().filter(o -> o.getProductType().equals(adjustment.getProductType())).count();
                    totalAdjustedAmount = adjustment.getAmount().multiply(new BigDecimal(count));
                    totalAmount = productWiseSale.getTotalAmount().subtract(totalAdjustedAmount);
                    break;
            }

            adjustmentDetails.setOperation(adjustment.getOperation());
            adjustmentDetails.setAmount(adjustment.getAmount());
            adjustmentDetails.setTotalAdjustedAmount(totalAdjustedAmount);
            adjustmentDetails.setTotalSaleAmount(totalAmount);
            productWiseSale.getAdjustments().add(adjustmentDetails);
        }
    }

    public List<Sale> getSaleRecord() {
        return saleRecord;
    }

    public List<Adjustment> getAdjustmentList() {
        return adjustmentList;
    }

    public Map<String, ProductWiseSale> getProductWiseSaleMap() {
        return productWiseSaleMap;
    }
}
