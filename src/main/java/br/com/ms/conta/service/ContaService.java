package br.com.ms.conta.service;

import br.com.ms.conta.dto.ContaDto;
import org.springframework.data.domain.Pageable;

public interface ContaService {

    Object buscar(String id, Pageable pageable);

    ContaDto.Response.Conta cadastrar(ContaDto.Request.Conta dto);
}
