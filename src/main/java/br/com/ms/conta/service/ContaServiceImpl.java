package br.com.ms.conta.service;

import br.com.ms.cartao.Cartao;
import br.com.ms.conta.Conta;
import br.com.ms.conta.dto.ContaDto;
import br.com.ms.conta.repository.ContaRepository;
import br.com.ms.conta.service.utils.ContaServiceUtils;
import br.com.ms.utils.service.DtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ContaServiceImpl implements ContaService{

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaServiceUtils contaServiceUtils;

    @Override
    public Object buscar(String id, Pageable pageable) {
        if(Objects.nonNull(id)){
            return DtoService.entityToDto(contaServiceUtils
                    .buscarConta(UUID.fromString(id)), ContaDto.Response.Conta.class);
        }

        Page<Conta> contas = this.contaRepository.findAll(pageable);
        return new PageImpl<>(DtoService.entitysToDtos(contas.getContent(), ContaDto.Response.Conta.class));
    }

    @Override
    public ContaDto.Response.Conta cadastrar(ContaDto.Request.Conta dto) {
        Conta conta = DtoService.dtoToEntity(dto, Conta.class);
        conta.setId(null);

        List<Cartao> cartoes = new ArrayList<>();

        if(!dto.getCartoes().isEmpty()){
            dto.getCartoes().forEach(cartaoDto -> {
                Cartao cartao = new Cartao();
                cartao.setConta(conta);
                cartao.setNome(cartaoDto.getNome());
                cartao.setNumero(cartaoDto.getNumero());
                cartao.setCvv(cartaoDto.getCVV());
                cartao.setDataValidade(cartaoDto.getDataValidade());

                cartoes.add(cartao);
            });
        }

        conta.setCartoes(cartoes);

        Conta contaPersistida = this.contaRepository.save(conta);
        return DtoService.entityToDto(contaPersistida, ContaDto.Response.Conta.class);
    }
}
