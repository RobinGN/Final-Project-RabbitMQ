package com.cetys.rabbit.testrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

@SpringBootApplication
public class TestrabbitmqApplication{

    public static void main(String[] args) throws Exception{
        SpringApplication.run(TestrabbitmqApplication.class, args);

        String queueName = System.getenv("QUEUE_NAME") != null
                ? System.getenv("QUEUE_NAME")
                : "test_messages";

        System.out.println("hello world");

        // Si no hay argumentos o contiene "receiver"
        if (args.length == 0 || Arrays.asList(args).contains("receiver")) {
            Receiver receiver = new Receiver(queueName);
            receiver.receiveMessage();
        }

        // Si no hay argumentos o contiene "listener"
        if (args.length == 0 || Arrays.asList(args).contains("listener")) {
            Receiver receiver = new Receiver(queueName);
            receiver.subscribeMessage();
        }

        // Si no hay argumentos o contiene "sender"
        if (args.length == 0 || Arrays.asList(args).contains("sender")) {
            Sender sender = new Sender(queueName);
            sender.sendMessage("hello world");
        }

        // Si no hay argumentos o contiene "publisher"
        if (args.length == 0 || Arrays.asList(args).contains("publisher")) {
            Sender sender = new Sender(queueName);
            sender.publishMessage("publishing hello");
        }
    }
}