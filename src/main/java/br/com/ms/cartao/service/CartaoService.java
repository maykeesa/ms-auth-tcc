package br.com.ms.cartao.service;

import br.com.ms.cartao.dto.CartaoDto;
import org.springframework.data.domain.Pageable;

public interface CartaoService {

    Object buscar(String id, Pageable pageable);

    CartaoDto.Response.Cartao cadastrar(CartaoDto.Request.Cartao dto);

    void deletar(String id);
}
