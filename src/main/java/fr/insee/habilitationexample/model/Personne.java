package fr.insee.habilitationexample.model;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode(of="id")
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Personne{
	
	@Id
	private Long id;
	@NonNull
	private String nom;
	@NonNull
	private String groupe;
	
}
