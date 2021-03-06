package fr.insee.habilitationexample.service;

import fr.insee.habilitationexample.model.Joueur;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface JoueurRepository extends CrudRepository<Joueur, Long>{
	
	Collection<Joueur> findByNom(String nom);

}
