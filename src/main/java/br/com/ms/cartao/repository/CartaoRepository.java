package br.com.ms.cartao.repository;

import br.com.ms.cartao.Cartao;
import br.com.ms.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, UUID> {

    List<Cartao> findByConta(Conta conta);
}
