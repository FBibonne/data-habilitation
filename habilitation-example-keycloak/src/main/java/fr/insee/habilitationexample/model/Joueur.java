package fr.insee.habilitationexample.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Getter
@Setter
@EqualsAndHashCode(of="id")
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Joueur {
	
	@Id
	@GeneratedValue
	private Long id;
	@NonNull
	private String nom;
	
}
