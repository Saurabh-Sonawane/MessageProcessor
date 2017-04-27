package com.mg.controller;

import com.mg.domain.Message;
import com.mg.domain.Operation;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class MessageProcessorTest {

    MessageProcessor messageProcessor = new MessageProcessor();

    @Test
    public void verifyProcessMessageRecordsSale() throws Exception {

        //Given
        List<Message> messagesList = new ArrayList<Message>();

        Message message = new Message();
        message.setProductType("Apple");
        message.setPricePerItem(new BigDecimal(10));
        message.setQuantity(1);
        messagesList.add(message);

        Message message1 = new Message();
        message1.setProductType("Samsung");
        message1.setPricePerItem(new BigDecimal(10));
        message1.setQuantity(1);
        messagesList.add(message1);

        //When
        messageProcessor.processMessage(message);
        messageProcessor.processMessage(message1);

        //Then
        assertTrue(messageProcessor.getSaleRecord().get(0).getProductType().equals(message.getProductType()));
        assertTrue(messageProcessor.getSaleRecord().get(0).getPricePerItem().equals(message.getPricePerItem()));
        assertTrue(messageProcessor.getSaleRecord().get(0).getQuantity().equals(message.getQuantity()));

        assertTrue(messageProcessor.getSaleRecord().get(1).getProductType().equals(message1.getProductType()));
        assertTrue(messageProcessor.getSaleRecord().get(1).getPricePerItem().equals(message1.getPricePerItem()));
        assertTrue(messageProcessor.getSaleRecord().get(1).getQuantity().equals(message1.getQuantity()));
    }

    @Test
    public void verifyProcessMessageSaleAdjustment() throws Exception {

        //Given
        List<Message> messagesList = new ArrayList<Message>();

        Message message = new Message();
        message.setProductType("Apple");
        message.setPricePerItem(new BigDecimal(10));
        message.setQuantity(1);
        messagesList.add(message);

        Message message1 = new Message();
        message1.setProductType("Samsung");
        message1.setPricePerItem(new BigDecimal(10));
        message1.setQuantity(1);
        messagesList.add(message1);

        Message message2 = new Message();
        message2.setProductType("Apple");
        message2.setOperation(Operation.ADD);
        message2.setAmount(new BigDecimal(50));
        messagesList.add(message2);

        //When
        messageProcessor.processMessage(message);
        messageProcessor.processMessage(message1);
        messageProcessor.processMessage(message2);

        //Then
        assertEquals("Apple", messageProcessor.getProductWiseSaleMap().get("Apple").getProductType());
        assertEquals(new BigDecimal(60), messageProcessor.getProductWiseSaleMap().get("Apple").getAdjustments().get(0).getTotalSaleAmount());
        assertEquals("1", messageProcessor.getProductWiseSaleMap().get("Apple").getTotalQuantity().toString());
    }

    @Test
    public void verifyProcessMessageRecordsAdjustment() throws Exception {

        //Given
        List<Message> messagesList = new ArrayList<Message>();

        Message message = new Message();
        message.setProductType("Apple");
        message.setPricePerItem(new BigDecimal(10));
        message.setQuantity(1);
        messagesList.add(message);

        Message message1 = new Message();
        message1.setProductType("Samsung");
        message1.setPricePerItem(new BigDecimal(10));
        message1.setQuantity(1);
        messagesList.add(message1);

        Message message2 = new Message();
        message2.setProductType("Apple");
        message2.setOperation(Operation.ADD);
        message2.setAmount(new BigDecimal(50));
        messagesList.add(message2);

        //When
        messageProcessor.processMessage(message);
        messageProcessor.processMessage(message1);
        messageProcessor.processMessage(message2);

        //Then
        assertEquals("Apple", messageProcessor.getAdjustmentList().get(0).getProductType());
        assertEquals(new BigDecimal(50), messageProcessor.getAdjustmentList().get(0).getAmount());
        assertEquals(Operation.ADD, messageProcessor.getAdjustmentList().get(0).getOperation());
    }



}