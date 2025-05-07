package br.com.ms.endereco.service;

import br.com.ms.conta.Conta;
import br.com.ms.conta.service.utils.ContaServiceUtils;
import br.com.ms.endereco.Endereco;
import br.com.ms.endereco.dto.EnderecoDto;
import br.com.ms.endereco.repository.EnderecoRepository;
import br.com.ms.utils.service.DtoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA;
import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA_EMAIL;

@Service
public class EnderecoServiceImpl implements EnderecoService{

    @Autowired
    private ContaServiceUtils contaServiceUtils;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Object buscar(String id, String email, Pageable pageable) {
        if(Objects.nonNull(id)){
            Endereco endereco = this.enderecoRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));

            return DtoService.entityToDto(endereco, EnderecoDto.Response.Endereco.class);
        }

        if(Objects.nonNull(email)){
            List<Endereco> enderecos = this.enderecoRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));

            EnderecoDto.Response.Enderecos response = new EnderecoDto.Response.Enderecos();
            List<EnderecoDto.Response.Base> enderecosDto =
                    DtoService.entitysToDtos(enderecos, EnderecoDto.Response.Base.class);

            if(enderecos.isEmpty()){
                throw new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA_EMAIL.getDescricao());
            }

            response.setEmail(enderecos.get(0).getEmail());
            response.setEnderecos(enderecosDto);

            return response;
        }

        Page<Endereco> enderecos = this.enderecoRepository.findAll(pageable);
        return new PageImpl<>(DtoService.entitysToDtos(enderecos.getContent(), EnderecoDto.Response.Endereco.class));
    }

    @Override
    public EnderecoDto.Response.Endereco cadastrar(EnderecoDto.Request.Endereco dto) {
        Conta conta = this.contaServiceUtils.buscarConta(UUID.fromString(dto.getContaId()));

        Endereco endereco = DtoService.dtoToEntity(dto, Endereco.class);
        endereco.setId(null);
        endereco.setConta(conta);
        endereco.setEmail(conta.getEmail());

        Endereco enderecoPersistido = this.enderecoRepository.save(endereco);
        return DtoService.entityToDto(enderecoPersistido, EnderecoDto.Response.Endereco.class);
    }

    @Override
    public void deletar(String id) {
        Endereco endereco = this.enderecoRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));

        this.enderecoRepository.delete(endereco);
    }
}
