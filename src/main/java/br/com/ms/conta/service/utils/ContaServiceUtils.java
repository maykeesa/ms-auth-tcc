package br.com.ms.conta.service.utils;

import br.com.ms.conta.Conta;
import br.com.ms.conta.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA;

@Service
public class ContaServiceUtils {

    @Autowired
    private ContaRepository contaRepository;

    public Conta buscarConta(UUID id){
        return this.contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));
    }
}
