package br.com.ms.cartao;

import br.com.ms.conta.Conta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cartoes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cartao {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String dataValidade;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private boolean padrao;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", conta='" + conta.getId() + '\'' +
                ", nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                ", dataValidade='" + dataValidade + '\'' +
                ", cvv='" + cvv + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
