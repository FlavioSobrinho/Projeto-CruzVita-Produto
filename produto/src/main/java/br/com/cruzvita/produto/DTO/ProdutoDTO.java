package br.com.cruzvita.produto.DTO;

import br.com.cruzvita.produto.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoDTO {
	
	private Status status;
	
	private String nome;
	
	private Double preco;
	
	private String descricao;
	
}
