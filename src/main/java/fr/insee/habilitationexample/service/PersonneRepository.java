package fr.insee.habilitationexample.service;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import fr.insee.habilitationexample.model.Personne;

public interface PersonneRepository extends CrudRepository<Personne, Long>{
	
	Collection<Personne> findByNom(String nom);
	
	Collection<Personne> findByGroupe(String groupe);

}
