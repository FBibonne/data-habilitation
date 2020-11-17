package fr.insee.habilitationexample.service;

import fr.insee.habilitationexample.model.Equipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface EquipeRepository extends CrudRepository<Equipe, Long> {

}
