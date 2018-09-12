package com.winxuan.sentinel.support.activemq.demo;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 使用JMS API往ActiveMQ发消息
 * @author xiejihan
 * @date 2018-08-27
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        String queueName = "cdfive";
        int messageCount = 2000;

        String brokerUrl = ActiveMQConnection.DEFAULT_BROKER_URL;// failover://tcp://localhost:61616

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        connectionFactory.setUseAsyncSend(true);

        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(queueName);
        MessageProducer messageProducer = session.createProducer(queue);

        long start = System.currentTimeMillis();
        for (int i = 0; i < messageCount; i++) {
            TextMessage textMessage = session.createTextMessage("hello " + (i+1));
            System.out.println("send message=>" + textMessage.getText());
            messageProducer.send(textMessage);
        }

        System.out.println("cost=" + (System.currentTimeMillis() - start) / 1000.0 + "s");

        session.close();
        connection.close();
    }
}