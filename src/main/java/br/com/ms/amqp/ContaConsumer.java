package br.com.ms.amqp;

import br.com.ms.conta.repository.ContaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaConsumer {

    @Autowired
    private ContaRepository contaRepository;

    @RabbitListener(queues = "pedido.conta.validacao")
    public boolean validarConta(String email) {
        return contaRepository.findByEmail(email).isPresent();
    }
}
