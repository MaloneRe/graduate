package com.personalizednewsrecommendation.manager.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;


/**
 * 
 * @author 
 * @description  Topic消息监听器
 * 
 */
@Component("topicReceiver")
public class TopicReceiver implements MessageListener{


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("TopicReceiver接收到消息:"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}