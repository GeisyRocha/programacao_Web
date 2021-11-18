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

import edu.ifam.aranoua.domain.Cliente;
import edu.ifam.aranoua.service.ClienteService;

@RestController
@RequestMapping(value="cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> listar(@PathVariable Integer id){
		Cliente cliente = clienteService.listar(id);
		
		return ResponseEntity.ok().body(cliente);
		
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Cliente> inserir (@RequestBody Cliente cliente){
		cliente = clienteService.inserir(cliente);

		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
		cliente= clienteService.atualizar(id, cliente);
		
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> excluir(@PathVariable Integer id) {
		clienteService.excluir(id);
		
		return ResponseEntity.noContent().build();	
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity <List<Cliente>> listarTodos() {
		List<Cliente> cliente = clienteService.listarTodos();
			
		return ResponseEntity.ok().body(cliente);	
	}
	@RequestMapping(value="/pages", method=RequestMethod.GET)
	public ResponseEntity <Page<Cliente>> listarPagina(
			@RequestParam (value="page", defaultValue = "0") Integer page,
			@RequestParam (value="size", defaultValue = "2")Integer size,
			@RequestParam (value="ord", defaultValue = "nome")String ord,
			@RequestParam (value="dir", defaultValue = "ASC")String dir) {
		Page<Cliente> cliente = clienteService.listarPagina(page, size, ord, dir);
		
		
		return ResponseEntity.ok().body(cliente);
		
	}
}
