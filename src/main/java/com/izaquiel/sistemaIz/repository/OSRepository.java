package com.izaquiel.sistemaIz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.izaquiel.sistemaIz.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {

}
