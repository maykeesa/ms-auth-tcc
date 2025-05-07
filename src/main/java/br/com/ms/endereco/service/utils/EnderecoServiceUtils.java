package br.com.ms.endereco.service.utils;

import br.com.ms.endereco.Endereco;
import br.com.ms.endereco.dto.EnderecoDto;
import br.com.ms.endereco.repository.EnderecoRepository;
import br.com.ms.utils.service.DtoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA;
import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA_EMAIL;

@Service
public class EnderecoServiceUtils {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco buscarEndereco(String id){
        return this.enderecoRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));
    }

    public EnderecoDto.Response.Enderecos buscarEnderecoPorEmail(String email){
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
}
