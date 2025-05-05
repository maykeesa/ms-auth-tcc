package br.com.ms.cartao;

import br.com.ms.cartao.dto.CartaoDto;
import br.com.ms.cartao.service.CartaoService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public ResponseEntity<Object> buscar(
            @RequestParam(required = false) String id,
            @PageableDefault(sort = "nome", size = 15, direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(this.cartaoService.buscar(id, pageable));
    }

    @PostMapping
    public ResponseEntity<CartaoDto.Response.Cartao> cadastrar(
            @RequestBody @Valid CartaoDto.Request.Cartao dto){
        return ResponseEntity.status(CREATED).body(this.cartaoService.cadastrar(dto));
    }
}
