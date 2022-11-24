package br.com.cruzvita.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.cruzvita.produto.model.Produto;

@EnableJpaRepositories
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}
