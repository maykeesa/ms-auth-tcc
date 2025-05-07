package br.com.ms.endereco.service;

import br.com.ms.endereco.dto.EnderecoDto;
import org.springframework.data.domain.Pageable;

public interface EnderecoService {

    Object buscar(String id, String email, Pageable pageable);

    EnderecoDto.Response.Endereco cadastrar(EnderecoDto.Request.Endereco dto);

    void deletar(String id);
}
