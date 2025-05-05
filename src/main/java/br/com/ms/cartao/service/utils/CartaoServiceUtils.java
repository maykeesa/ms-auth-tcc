package br.com.ms.cartao.service.utils;

import br.com.ms.cartao.Cartao;
import br.com.ms.cartao.repository.CartaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA;

@Service
public class CartaoServiceUtils {

    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao buscarCartao(UUID id){
        return this.cartaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));
    }
}
