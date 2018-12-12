package com.shterneregen.RabbitService;

import com.shterneregen.RabbitService.listener.RequestListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class RabbitServiceApplication {

    private final Environment env;

    @Autowired
    public RabbitServiceApplication(Environment env) {
        this.env = env;
    }

    @Bean
    Queue queue() {
        return new Queue(env.getProperty("message.queue"), false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(env.getProperty("topic.name"));
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(env.getProperty("message.queue"));
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(env.getProperty("message.queue"));
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RequestListener receiver) {
        return new MessageListenerAdapter(receiver, "process");
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitServiceApplication.class, args);
    }
}
