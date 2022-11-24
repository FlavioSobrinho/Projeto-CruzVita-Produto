package br.com.cruzvita.produto.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cruzvita.produto.DTO.ProdutoDTO;
import br.com.cruzvita.produto.service.ProdutoService;

@RestController //Indicar ao Spring que é uma classe de Controller //Na Classe Controller ficam todos os arquivos que funcionam como controladores dos dados.// A camada de controle é responsável por intermediar as requisições enviadas pelo View com as respostas fornecidas pelo Model, processando os dados que o usuário informou e repassando para outras camadas.
@RequestMapping("/produtos") //Anotação para Indicar o ENDPOINT
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public Page<ProdutoDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
	        return service.obterTodos(paginacao);
	    }
	 
	@GetMapping("/{id}") //Precisa passar o ID, e pra isso Precisamos passar o ID No Caminho do Endereço(ENDPOINT) Pra isso Usamos a anotação @PathVariable
	    public ResponseEntity<ProdutoDTO> detalhar(@PathVariable @NotNull Long id) {
			ProdutoDTO dto = service.produtoPorId(id);

	        return ResponseEntity.ok(dto);
	    }
	
	 @PutMapping("/{id}") //RequestBody serve para Indicar no corpo da requisição o JSON para Atualizar o Produto
	    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid ProdutoDTO dto) {
		 ProdutoDTO atualizado = service.atualizarProduto(id, dto);
	        return ResponseEntity.ok(atualizado);
	    }
	
    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody @Valid ProdutoDTO dto, UriComponentsBuilder uriBuilder) {
    	ProdutoDTO produto = service.criarProduto(dto);
        URI endereco = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(endereco).body(produto);
    }
    
    @DeleteMapping("/{id}") 
    public ResponseEntity<ProdutoDTO> remover(@PathVariable @NotNull Long id) {
        service.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
    
}
