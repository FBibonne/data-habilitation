package fr.insee.habilitationexample.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@ToString(of={"id","nom"})
@Entity
public class Equipe {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nom;

    @OneToMany(orphanRemoval = true)
    private List<Joueur> joueurs =new ArrayList<>();
}
