package br.com.ms.conta;

import br.com.ms.cartao.Cartao;
import br.com.ms.cartao.dto.CartaoDto;
import br.com.ms.endereco.Endereco;
import br.com.ms.endereco.dto.EnderecoDto;
import br.com.ms.utils.service.DtoService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Conta {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cartao> cartoes;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Endereco> enderecos;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cartoes=" + DtoService.entitysToDtos(cartoes, CartaoDto.Response.Cartao.class) +
                ", enderecos=" + DtoService.entitysToDtos(enderecos, EnderecoDto.Response.Endereco.class) +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
