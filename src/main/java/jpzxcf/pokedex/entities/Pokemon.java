package jpzxcf.pokedex.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "pokemons")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "nome_pokemon")
    private String nome;
    @ManyToMany
    private Set<TipoPokemon> tipo;
    private Float altura;
    private Float peso;
}
