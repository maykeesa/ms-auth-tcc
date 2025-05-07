package br.com.ms.endereco;

import br.com.ms.endereco.dto.EnderecoDto;
import br.com.ms.endereco.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<Object> buscar(
            @RequestParam(required = false) String id, @RequestParam(required = false) String email,
            @PageableDefault(sort = "dataCriacao", size = 15, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(this.enderecoService.buscar(id, email, pageable));
    }

    @PostMapping
    public ResponseEntity<EnderecoDto.Response.Endereco> cadastrar(
            @RequestBody @Valid EnderecoDto.Request.Endereco dto){
        return ResponseEntity.status(CREATED).body(this.enderecoService.cadastrar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){
        this.enderecoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
