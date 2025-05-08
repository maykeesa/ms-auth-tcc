package br.com.ms.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContaAMQPConfiguration {

    @Bean
    public Queue criarFilaContaValidacaoResposta() {
        return QueueBuilder.nonDurable("conta.validacao.resposta").build();
    }
}
