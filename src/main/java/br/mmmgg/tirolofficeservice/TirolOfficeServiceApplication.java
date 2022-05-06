package br.mmmgg.tirolofficeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.mmmgg.tirolofficeservice.controller.UserController;

@SpringBootApplication
@ComponentScan({"br.mmmgg.tirolofficeservice.controller", "br.mmmgg.tirolofficeservice.service", "br.mmmgg.tirolofficeservice.security"})
@EntityScan("br.mmmgg.tirolofficeservice.model")
@EnableJpaRepositories("br.mmmgg.tirolofficeservice.repository")
public class TirolOfficeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TirolOfficeServiceApplication.class, args);
	}

}
