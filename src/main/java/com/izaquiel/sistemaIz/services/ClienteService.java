package com.izaquiel.sistemaIz.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izaquiel.sistemaIz.domain.Cliente;
import com.izaquiel.sistemaIz.domain.pessoa;
import com.izaquiel.sistemaIz.dtos.ClienteDTO;
import com.izaquiel.sistemaIz.repository.PessoaRepository;
import com.izaquiel.sistemaIz.repository.ClienteRepository;
import com.izaquiel.sistemaIz.resources.exceptons.DataIntegratyViolationException;
import com.izaquiel.sistemaIz.services.exceptions.ObjectNotFoundException;




@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository; 
	
	public Cliente findByInd(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: "  + id + ", Tipo: " + Cliente.class.getName()));
		
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	} 
	
	public Cliente create(ClienteDTO objDTO){
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF cadastro ja na base de dDOS");
		}
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
	
		Cliente oldObj = findByInd(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF cadastro ja na base de dDOS");
			
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	/*
	 * metodo para o delete do clienteresource
	 */
	public void delete(Integer id) {
		Cliente obj = findByInd(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente pertece a ordem de serviço: ");
		}
		repository.deleteById(id);
		
	}
	
	private pessoa findByCPF(ClienteDTO objDTO){
		pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
		

	}

	

	
}
