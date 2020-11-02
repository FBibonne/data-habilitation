package fr.insee.habilitationexample;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;

import fr.insee.habilitationexample.model.Personne;
import fr.insee.habilitationexample.service.PersonneRepository;

@SpringBootApplication
@EnableMapRepositories
public class HabilitationExampleApplication {
	
	@Autowired 
	private PersonneRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(HabilitationExampleApplication.class, args);
	}
	
	

	public @PostConstruct void init() {

		repository.save(new Personne("Kyrie irving","Brooklyn"));
		repository.save(new Personne("Vincent Poirier", "Celtics"));
	}

}
