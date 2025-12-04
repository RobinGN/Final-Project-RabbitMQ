package com.cetys.rabbit.testrabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receiver {
    String QUEUE_NAME;
    ConnectionFactory connectionFactory;

    public Receiver(String queue) {
        QUEUE_NAME = queue;
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
    }

    public void receiveMessage() throws Exception {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[X] Received ' " + message+ "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }

    public void subscribeMessage() throws Exception {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(QUEUE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, QUEUE_NAME, "");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[X] Received ' " + message+ "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
