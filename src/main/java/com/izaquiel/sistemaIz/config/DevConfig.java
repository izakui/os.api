package com.izaquiel.sistemaIz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.izaquiel.sistemaIz.services.DBservices;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBservices dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;
	
	@Bean
	public boolean instaciaDB() {
		
		if(ddl.equals("create")){
			this.dbService.instaciaDb();
		}
		return false;

		

	}

}
