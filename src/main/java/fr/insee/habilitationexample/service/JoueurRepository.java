package fr.insee.habilitationexample.service;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import fr.insee.habilitationexample.model.Joueur;

public interface JoueurRepository extends CrudRepository<Joueur, Long>{
	
	Collection<Joueur> findByNom(String nom);

}
