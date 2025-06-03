package br.com.ms.conta;

import br.com.ms.conta.dto.ContaDto;
import br.com.ms.conta.service.ContaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<Object> buscar(
            @RequestParam(required = false) String id,
            @PageableDefault(sort = "nome", size = 15, direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(this.contaService.buscar(id, pageable));
    }

    @PostMapping
    public ResponseEntity<ContaDto.Response.Conta> cadastrar(
            @RequestBody @Valid ContaDto.Request.Conta dto){
        log.info("----------------------------------------------------");
        log.info("Conta | Inicio cadastrar()");
        ResponseEntity<ContaDto.Response.Conta> response =
                ResponseEntity.status(CREATED).body(this.contaService.cadastrar(dto));
        log.info("Conta | Fim cadastrar()");
        log.info("----------------------------------------------------");
        return response;
    }
}
