package br.com.ms.conta.dto;

import br.com.ms.cartao.dto.CartaoDto;
import br.com.ms.endereco.dto.EnderecoDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.ms.utils.constants.MensagensConstants.NAO_NULO;
import static br.com.ms.utils.constants.MensagensConstants.NAO_NULO_BRANCO;

public class ContaDto {

    public enum Request{;

        @Data
        public static class Cartao{
            @NotBlank(message = NAO_NULO_BRANCO)
            private String nome;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String numero;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String dataValidade;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String CVV;
            @NotNull(message = NAO_NULO)
            private boolean padrao;
        }

        @Data
        public static class Endereco {
            @NotBlank(message = NAO_NULO_BRANCO)
            private String logradouro;
            @NotNull(message = NAO_NULO)
            private Integer numero;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String complemento;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String bairro;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String cidade;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String estado;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String cep;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String pais;
            @NotNull(message = NAO_NULO)
            private boolean padrao;
        }

        @Data
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Conta{
            @NotBlank(message = NAO_NULO_BRANCO)
            private String email;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String nome;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String cpf;
            private List<ContaDto.Request.Cartao> cartoes;
            private List<ContaDto.Request.Endereco> enderecos;
        }
    }

    public enum Response{;

        @Data
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Conta{
            private UUID id;
            private String email;
            private String nome;
            private String cpf;
            private List<CartaoDto.Response.Cartao> cartoes;
            private List<EnderecoDto.Response.Endereco> enderecos;
            private LocalDateTime dataCriacao;
        }

    }
}
