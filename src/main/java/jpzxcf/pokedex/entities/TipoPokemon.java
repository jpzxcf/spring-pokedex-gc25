package jpzxcf.pokedex.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
public class TipoPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NomeTipo nome;

    @ManyToMany
    private Set<Pokemon> pokemonSet;

    public enum NomeTipo{
        AGUA,
        VENENOSO,
        FOGO,
    }





}
