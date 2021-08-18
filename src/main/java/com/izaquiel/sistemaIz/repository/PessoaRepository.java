package com.izaquiel.sistemaIz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.izaquiel.sistemaIz.domain.pessoa;




@Repository
public interface PessoaRepository extends JpaRepository<pessoa, Integer> {

	@Query("SELECT obj FROM pessoa obj WHERE obj.cpf =:cpf")
	pessoa findByCPF(@Param("cpf") String cpf);

}
