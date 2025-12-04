package com.cetys.rabbit.testrabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Sender {
    String QUEUE_NAME;
    ConnectionFactory connectionFactory;

    public Sender(String queue){
        QUEUE_NAME=queue;
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
    }

    public void sendMessage(String message) throws Exception{
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }

    public void publishMessage(String message) throws Exception{
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //String otroQueue = "aksnmdkladsm";
        channel.exchangeDeclare(QUEUE_NAME, "fanout");
        //.basicPublish (Broadcast_channel_name, Queue_channel_name, ...)
        //channel.basicPublish(QUEUE_NAME, otroQueue, null, message.getBytes());
        channel.basicPublish(QUEUE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
