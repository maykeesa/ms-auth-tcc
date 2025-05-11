package br.com.ms.amqp;

import br.com.ms.cartao.Cartao;
import br.com.ms.cartao.dto.CartaoDto;
import br.com.ms.conta.Conta;
import br.com.ms.conta.repository.ContaRepository;
import br.com.ms.utils.service.DtoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA;

@Service
public class ContaConsumer {

    @Autowired
    private ContaRepository contaRepository;

    @RabbitListener(queues = "pedido.conta.validacao")
    public boolean validarConta(String email) {
        return contaRepository.findByEmail(email).isPresent();
    }

    @Transactional(readOnly = true)
    @RabbitListener(queues = "pagamento.conta.cartao")
    public CartaoDto.Response.Cartao buscarCartaoCreditoEDebito(String email) {
        Conta conta = contaRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));

        List<Cartao> cartoes = conta.getCartoes();
        Cartao cartao = cartoes.stream()
                .filter(Cartao::isPadrao)
                .findFirst()
                .orElse(null);

        return DtoService.entityToDto(cartao, CartaoDto.Response.Cartao.class);
    }
}
