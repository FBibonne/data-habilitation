package fr.insee.habilitationexample.model;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
