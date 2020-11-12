package fr.insee.habilitationexample;

import fr.insee.datahabilitation.AccessControlRegister;
import fr.insee.habilitationexample.model.Equipe;
import fr.insee.habilitationexample.model.Joueur;
import fr.insee.habilitationexample.service.EquipeRepository;
import fr.insee.habilitationexample.service.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.SpringServletContainerInitializer;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"fr.insee.habilitationexample", "fr.insee.datahabilitation"})
public class HabilitationExampleApplication {
	
	@Autowired 
	private EquipeRepository equipeRepository;
	@Autowired
	private JoueurRepository joueurRepository;

	public static void main(String[] args) {
		SpringApplication.run(HabilitationExampleApplication.class, args);
	}



	@PostConstruct
	public void init() {

		Equipe nets = new Equipe("Nets");
		Equipe celtics=new Equipe("Celtics");
		Equipe hornets=new Equipe("Hornets");

		Joueur joueur =new Joueur("Kyrie irving");
		joueur = joueurRepository.save(joueur);
		nets.getJoueurs().add(joueur);
		joueur =new Joueur("Timothé Luwawu-Cabarrot");
		joueur = joueurRepository.save(joueur);
		nets.getJoueurs().add(joueur);

		joueur =new Joueur("Vincent Poirier");
		joueur = joueurRepository.save(joueur);
		celtics.getJoueurs().add(joueur);

		equipeRepository.save(nets);
		equipeRepository.save(celtics);
		equipeRepository.save(hornets);

	}

}
