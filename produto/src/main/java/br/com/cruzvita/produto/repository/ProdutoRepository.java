package br.com.cruzvita.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cruzvita.produto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}
