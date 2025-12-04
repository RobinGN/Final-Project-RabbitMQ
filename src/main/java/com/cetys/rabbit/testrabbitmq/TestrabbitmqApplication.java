package com.cetys.rabbit.testrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestrabbitmqApplication{

	public static void main(String[] args) throws Exception{
        SpringApplication.run(TestrabbitmqApplication.class, args);
        System.out.println("hello world");

        Sender sender = new Sender("test_messages");
        sender.sendMessage("hello world");
        sender.publishMessage("publishing hello");

        Receiver receiver = new Receiver("test_messages");
        receiver.receiveMessage();
        receiver.subscribeMessage();
	}
}
