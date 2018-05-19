package com.personalizednewsrecommendation.manager.activemq;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("crawlQueueSender")
public class CrawlQueueSender {
	 @Autowired
	 @Qualifier("jmsQueueTemplate")//通过@Qualifier修饰符来注入对应的bean
	 private JmsTemplate jmsTemplate;
	 
	 public void send(String queueName,Object object){
         jmsTemplate.send(queueName, new MessageCreator() {
        	 @Override
        	 public Message createMessage(Session session) throws JMSException {
        		 return session.createObjectMessage( (Serializable) object);
        	 }
         });
	 }
}
