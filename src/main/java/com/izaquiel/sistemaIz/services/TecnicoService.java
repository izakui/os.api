package com.izaquiel.sistemaIz.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izaquiel.sistemaIz.domain.Tecnico;
import com.izaquiel.sistemaIz.domain.pessoa;
import com.izaquiel.sistemaIz.dtos.TecnicoDTO;
import com.izaquiel.sistemaIz.repository.PessoaRepository;
import com.izaquiel.sistemaIz.repository.TecnicoRepository;
import com.izaquiel.sistemaIz.resources.exceptons.DataIntegratyViolationException;
import com.izaquiel.sistemaIz.services.exceptions.ObjectNotFoundException;




@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository; 
	
	public Tecnico findByInd(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: "  + id + ", Tipo: " + Tecnico.class.getName()));
		
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	} 
	
	public Tecnico create(TecnicoDTO objDTO){
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF cadastro ja na base de dDOS");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
	
		Tecnico oldObj = findByInd(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF cadastro ja na base de dDOS");
			
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	/*
	 * metodo para o delete do tecnicoresource
	 */
	public void delete(Integer id) {
		Tecnico obj = findByInd(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico pertece a ordem de serviço: ");
		}
		repository.deleteById(id);
		
	}
	
	private pessoa findByCPF(TecnicoDTO objDTO){
		pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
		

	}

	

	
}
