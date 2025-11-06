package jpzxcf.pokedex.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Table(name = "tipoPokemon")
@Entity
public class TipoPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NomeTipo nome;

    @ManyToMany(mappedBy = "tipo")
    private Set<Pokemon> pokemonSet;

    public enum NomeTipo{
        AGUA,
        VENENOSO,
        FOGO,
    }





}
