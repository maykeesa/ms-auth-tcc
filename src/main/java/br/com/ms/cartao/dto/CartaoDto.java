package br.com.ms.cartao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.ms.utils.constants.MensagensConstants.NAO_NULO_BRANCO;

public class CartaoDto {

    public enum Request{;

        @Data
        public static class Cartao{
            @NotNull(message = NAO_NULO_BRANCO)
            private String contaId;
            @NotNull(message = NAO_NULO_BRANCO)
            private String nome;
            @NotNull(message = NAO_NULO_BRANCO)
            private String numero;
            @NotNull(message = NAO_NULO_BRANCO)
            private String dataValidade;
            @NotNull(message = NAO_NULO_BRANCO)
            private String cvv;
        }
    }

    public enum Response{;

        @Data
        public static class Cartao{
            private UUID id;
            private UUID contaId;
            private String nome;
            private String numero;
            private String dataValidade;
            private String cvv;
            private boolean padrao;
            private LocalDateTime dataCriacao;
        }
    }
}
