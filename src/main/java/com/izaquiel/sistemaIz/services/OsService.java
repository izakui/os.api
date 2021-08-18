package com.izaquiel.sistemaIz.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izaquiel.sistemaIz.domain.Cliente;
import com.izaquiel.sistemaIz.domain.OS;
import com.izaquiel.sistemaIz.domain.Tecnico;
import com.izaquiel.sistemaIz.domain.enuns.Prioridade;
import com.izaquiel.sistemaIz.domain.enuns.Status;
import com.izaquiel.sistemaIz.dtos.OSDTO;
import com.izaquiel.sistemaIz.repository.OSRepository;
import com.izaquiel.sistemaIz.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;

	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipos: " + OS.class.getName()));

	}

	public List<OS> findAll() {
		return repository.findAll();
	}

	public OS create(@Valid OSDTO obj) {

		return fromDTO(obj);
	}

	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
		


	}

	private OS fromDTO(OSDTO obj) {
		OS newOBJ = new OS();
		newOBJ.setId(obj.getId());
		newOBJ.setObservacoes(obj.getObservacoes());
		newOBJ.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newOBJ.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tec = tecnicoService.findByInd(obj.getTecnico());
		Cliente cli = clienteService.findByInd(obj.getCliente());

		newOBJ.setTecnico(tec);
		newOBJ.setCliente(cli);
		
		if(newOBJ.getStatus().getCod().equals(2)) {
			newOBJ.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newOBJ);
	}

}
