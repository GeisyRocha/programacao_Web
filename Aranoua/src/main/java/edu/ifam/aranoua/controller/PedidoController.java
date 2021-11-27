package edu.ifam.aranoua.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.ifam.aranoua.domain.Pedido;
import edu.ifam.aranoua.service.PedidoService;



@RestController 
@RequestMapping(value="pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> listar(@PathVariable Integer id){
		Pedido pedido = pedidoService.listar(id);
		
		return ResponseEntity.ok().body(pedido);
		
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Pedido> inserir (@RequestBody Pedido pedido){
		pedido = pedidoService.inserir(pedido);

		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
		 
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pedido> atualizar(@PathVariable Integer id, @RequestBody Pedido pedido) {
		pedido= pedidoService.atualizar(id, pedido);
			
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Pedido> excluir(@PathVariable Integer id) {
	            pedidoService.excluir(id);
		
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity <List<Pedido>>listarTodos() {
		List<Pedido> pedido = pedidoService.listarTodos();
			
		return ResponseEntity.ok().body(pedido);
		
}
	@RequestMapping(value="/pages", method=RequestMethod.GET)
	public ResponseEntity <Page<Pedido>> listarPagina(
			@RequestParam (value="page", defaultValue = "0") Integer page,
			@RequestParam (value="size", defaultValue = "2")Integer size,
			@RequestParam (value="ord", defaultValue = "data")String ord,
			@RequestParam (value="dir", defaultValue = "ASC")String dir) {
		Page<Pedido> pedido = pedidoService.listarPagina(page, size, ord, dir);
		
		
		return ResponseEntity.ok().body(pedido);
		
	}

}
