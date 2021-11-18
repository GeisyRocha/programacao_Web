package edu.ifam.aranoua.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.ifam.aranoua.domain.Produto;
import edu.ifam.aranoua.repository.ProdutoRepository;
import edu.ifam.aranoua.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto listar (Integer id) {
		Optional<Produto> produto =produtoRepository.findById(id);
		return produto.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID:" + id + ", Tipo: " + Produto.class.getName()));
	}

	 public Produto inserir(Produto produto) {
	    	return produtoRepository.save(produto);
	    	
	 }    	
	 public Produto atualizar(Integer id, Produto produto) {
			produto.setId(id);
			return produtoRepository.save(produto);
		}
	 public void excluir(Integer id) {
		 listar(id);
			produtoRepository.deleteById(id);
		}
	 public List<Produto> listarTodos() {
			
			return produtoRepository.findAll();
	    }
	
	 public Page<Produto> listarPagina(Integer page,Integer size,String ord,String dir){
	    	PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(dir),ord);
	    	return produtoRepository.findAll(pageRequest);
	    }
}
