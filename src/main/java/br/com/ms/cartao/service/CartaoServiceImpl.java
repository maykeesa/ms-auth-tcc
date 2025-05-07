package br.com.ms.cartao.service;

import br.com.ms.cartao.Cartao;
import br.com.ms.cartao.dto.CartaoDto;
import br.com.ms.cartao.repository.CartaoRepository;
import br.com.ms.cartao.service.utils.CartaoServiceUtils;
import br.com.ms.conta.Conta;
import br.com.ms.conta.service.utils.ContaServiceUtils;
import br.com.ms.utils.service.DtoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA;

@Service
public class CartaoServiceImpl implements CartaoService{

    @Autowired
    private CartaoServiceUtils cartaoServiceUtils;
    @Autowired
    private ContaServiceUtils contaServiceUtils;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Override
    public Object buscar(String id, Pageable pageable) {
        if(Objects.nonNull(id)){
            return DtoService.entityToDto(cartaoServiceUtils
                    .buscarCartao(UUID.fromString(id)), CartaoDto.Response.Cartao.class);
        }

        Page<Cartao> cartoes = this.cartaoRepository.findAll(pageable);
        return new PageImpl<>(DtoService.entitysToDtos(cartoes.getContent(), CartaoDto.Response.Cartao.class));
    }

    @Override
    public CartaoDto.Response.Cartao cadastrar(CartaoDto.Request.Cartao dto) {
        Conta conta = this.contaServiceUtils.buscarConta(UUID.fromString(dto.getContaId()));

        Cartao cartao = DtoService.dtoToEntity(dto, Cartao.class);
        cartao.setId(null);
        cartao.setConta(conta);

        Cartao cartaoPersistido = this.cartaoRepository.save(cartao);
        return DtoService.entityToDto(cartaoPersistido, CartaoDto.Response.Cartao.class);
    }

    @Override
    public void deletar(String id) {
        Cartao cartao = this.cartaoRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));

        this.cartaoRepository.delete(cartao);
    }
}
