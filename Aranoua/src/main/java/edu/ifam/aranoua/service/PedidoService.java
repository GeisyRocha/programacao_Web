package edu.ifam.aranoua.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.ifam.aranoua.domain.Pedido;
import edu.ifam.aranoua.repository.ItemPedidoRepository;
import edu.ifam.aranoua.repository.PedidoRepository;
import edu.ifam.aranoua.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	 @Autowired
	 ItemPedidoRepository itemPedidoRepository;
	 
	public Pedido  listar (Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID:" + id + ", Tipo: " + Pedido.class.getName()));
		
	}

	 public Pedido inserir(Pedido pedido) {
	    	pedido = pedidoRepository.save(pedido);
	    	
	   	 itemPedidoRepository.saveAll(pedido.getItensPedido());
	   	 
	 	return pedido;
	 }   
	 

	 
	 public Pedido atualizar(Integer id, Pedido pedido) {
			pedido.setId(id);
			return pedidoRepository.save(pedido);
		}
	 
	 public void excluir(Integer id) {
		 listar(id);
			pedidoRepository.deleteById(id);
		}
	 public List<Pedido> listarTodos() {
			
			return pedidoRepository.findAll();
	    }
	 public Page<Pedido> listarPagina(Integer page,Integer size,String ord,String dir){
	    	PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(dir),ord);
	    	return pedidoRepository.findAll(pageRequest);
	    
	}
	
}


