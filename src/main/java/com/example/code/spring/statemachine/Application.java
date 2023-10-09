package com.example.code.spring.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

/**
 * className Application
 * packageName com.example.code.spring.statemachine
 * description
 *
 * @author CCC
 * @date 2021/1/18 23:39
 */
@SpringBootApplication(exclude = ElasticsearchRestClientAutoConfiguration.class)
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        Message<Events> message = MessageBuilder.withPayload(Events.RECEIVE).setHeader("order", "order1").build();
        stateMachine.sendEvent(message);
    }

}