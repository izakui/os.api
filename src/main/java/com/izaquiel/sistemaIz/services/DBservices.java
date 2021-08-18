package com.izaquiel.sistemaIz.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izaquiel.sistemaIz.domain.Cliente;
import com.izaquiel.sistemaIz.domain.OS;
import com.izaquiel.sistemaIz.domain.Tecnico;
import com.izaquiel.sistemaIz.domain.enuns.Prioridade;
import com.izaquiel.sistemaIz.domain.enuns.Status;
import com.izaquiel.sistemaIz.repository.ClienteRepository;
import com.izaquiel.sistemaIz.repository.OSRepository;
import com.izaquiel.sistemaIz.repository.TecnicoRepository;

@Service
public class DBservices {
	
	@Autowired
	private TecnicoRepository tecnicoRepositorio;
	@Autowired
	private ClienteRepository clienteRepositorio;
	@Autowired
	private OSRepository osRepositorio;
	
	public void instaciaDb() {
		
		Tecnico t1 = new Tecnico(null, "Izaquiel timote", "144.785.300-84", "(32) 98888-8888");
		Cliente c1 = new Cliente(null, "betina de souza", "598.508.200-80", "(32) 98888-7777");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste de creação", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepositorio.saveAll(Arrays.asList(t1));
		clienteRepositorio.saveAll(Arrays.asList(c1));
		osRepositorio.saveAll(Arrays.asList(os1));
		
	} 

}
