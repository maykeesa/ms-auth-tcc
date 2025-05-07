package br.com.ms.endereco.repository;

import br.com.ms.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    Optional<List<Endereco>> findByEmail(String email);
}
