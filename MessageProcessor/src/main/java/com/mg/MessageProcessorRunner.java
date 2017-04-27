package com.mg;

import com.mg.controller.MessageProcessor;
import com.mg.domain.Message;
import com.mg.domain.Operation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * MessageProcessorRunner main class
 */
public class MessageProcessorRunner {

    public static void main(String[] args) {
        MessageProcessor messageProcessor = new MessageProcessor();
        List<Message> messagesList = new ArrayList<Message>();
        for (int i=0; i < 60; i++) {
            if(i%5 == 0) {
                Message message = new Message();
                message.setProductType("Apple");
                message.setPricePerItem(new BigDecimal(10));
                message.setQuantity(2);
                messagesList.add(message);
            }
            if(i%5 == 1) {
                Message message = new Message();
                message.setProductType("Samsung");
                message.setPricePerItem(new BigDecimal(10));
                message.setQuantity(1);
                messagesList.add(message);
            }
            if(i%5 == 2) {
                Message message = new Message();
                message.setProductType("Nokia");
                message.setPricePerItem(new BigDecimal(10));
                message.setQuantity(5);
                messagesList.add(message);
            }
            if(i%5 == 3) {
                Message message1 = new Message();
                message1.setProductType("Samsung");
                message1.setOperation(Operation.MULTIPLY);
                message1.setAmount(new BigDecimal(10));
                messagesList.add(message1);
            }
            if(i%5 == 4) {
                Message message1 = new Message();
                message1.setProductType("Nokia");
                message1.setOperation(Operation.ADD);
                message1.setAmount(new BigDecimal(10));
                messagesList.add(message1);
            }
        }

        for (Message message : messagesList) {
            messageProcessor.processMessage(message);
        }

    }
}
