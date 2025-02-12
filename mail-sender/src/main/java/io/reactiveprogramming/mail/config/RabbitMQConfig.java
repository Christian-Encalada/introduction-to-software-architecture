package io.reactiveprogramming.mail.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.addresses}")
    private String addresses;

    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri(addresses);
        
        // Configuración específica de SSL
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory = connectionFactory.getRabbitConnectionFactory();
        rabbitConnectionFactory.useSslProtocol("TLSv1.2");
        
        // Configurar timeout
        rabbitConnectionFactory.setConnectionTimeout(30000);
        rabbitConnectionFactory.setHandshakeTimeout(30000);
        
        return connectionFactory;
    }
} 