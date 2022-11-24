package br.com.cruzvita.produto.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cruzvita.produto.DTO.ProdutoDTO;
import br.com.cruzvita.produto.model.Produto;
import br.com.cruzvita.produto.model.Status;
import br.com.cruzvita.produto.repository.ProdutoRepository;

@Service // Uma classe serviço é aquela que possui comportamentos que não se encaixam em outras classes de forma natural e encapsulada.
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	//ModelMapper:Transfere os dados entre a entidade e o DTO
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<ProdutoDTO> obterTodos(Pageable paginacao){
		return repository.findAll(paginacao).map(p -> modelMapper.map(p, ProdutoDTO.class));
	}	

		public ProdutoDTO produtoPorId(Long id) {
			
			Produto produto = repository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException());
			
			return modelMapper.map(produto, ProdutoDTO.class);
		}
		
		//Transformar de ProdutoDTO Para Produto, colocar o Status Disponivel, depois de salvo Pego o Pagamento e tranformar em DTO e devolver para a Controller.
		
		public ProdutoDTO criarProduto(ProdutoDTO produtoDto) {
			Produto produto = modelMapper.map(produtoDto, Produto.class);
			produto.setStatus(Status.DISPONIVEL);
			repository.save(produto);
			
			return modelMapper.map(produto, ProdutoDTO.class);
		}
		
		  public ProdutoDTO atualizarProduto(Long id, ProdutoDTO dto) {
		        Produto produto = modelMapper.map(dto, Produto.class);
		        produto.setId(id);
		        produto = repository.save(produto);
		        return modelMapper.map(produto, ProdutoDTO.class);
		    }
		  
		  //Recebe o Id do Produto e Apaga.
		  public void excluirProduto(Long id) {
		        repository.deleteById(id);
		    }
}
