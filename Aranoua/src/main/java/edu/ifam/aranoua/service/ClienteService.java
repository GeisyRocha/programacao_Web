package edu.ifam.aranoua.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.ifam.aranoua.domain.Cliente;
import edu.ifam.aranoua.repository.ClienteRepository;
import edu.ifam.aranoua.service.exception.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente listar (Integer id) {
		Optional<Cliente> cliente =clienteRepository.findById(id);
		return cliente.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID:" + id + ", Tipo: " + Cliente.class.getName()));
	}
	 public Cliente inserir(Cliente cliente) {
	    	return clienteRepository.save(cliente);
	    	
	 }   
	 public Cliente atualizar(Integer id, Cliente cliente) {
			cliente.setId(id);
			return clienteRepository.save(cliente);
		}
	 public void excluir(Integer id) {
		 listar(id);
			clienteRepository.deleteById(id);
		}
	 public List<Cliente> listarTodos() {
			
			return clienteRepository.findAll();
	    }
	 public Page<Cliente> listarPagina(Integer page,Integer size,String ord,String dir){
	    	PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(dir),ord);
	    	return clienteRepository.findAll(pageRequest);
	    }
	}
	

