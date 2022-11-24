package br.com.cruzvita.produto.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // <- TRANSFORMA A CLASSE EM UMA ENTIDADE ( OBJETO )
@Table(name = "produtos")
@Data
@NoArgsConstructor //<- GERA 1 CONTRUTOR COM PARAMETROS PARA CADA ATRIBUTO DA CLASSE


public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max=100)
	private String nome;
	
	@NotNull
	@Positive
	private Double preco;
	
	@NotNull
	@Size(max=200)
	@Size(min=10)
	private String descricao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
}
