package br.com.ms.conta.dto;

import br.com.ms.cartao.dto.CartaoDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.ms.utils.constants.MensagensConstants.NAO_NULO_BRANCO;

public class ContaDto {

    public enum Request{;

        @Data
        public static class Cartao{
            @NotNull(message = NAO_NULO_BRANCO)
            private String nome;
            @NotNull(message = NAO_NULO_BRANCO)
            private String numero;
            @NotNull(message = NAO_NULO_BRANCO)
            private String dataValidade;
            @NotNull(message = NAO_NULO_BRANCO)
            private String CVV;
        }

        @Data
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Conta{
            @NotNull(message = NAO_NULO_BRANCO)
            private String email;
            @NotNull(message = NAO_NULO_BRANCO)
            private String nome;
            @NotNull(message = NAO_NULO_BRANCO)
            private String cpf;
            private List<ContaDto.Request.Cartao> cartoes;
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
            private LocalDateTime dataCriacao;
        }

    }
}
