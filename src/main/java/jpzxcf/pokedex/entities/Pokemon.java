package jpzxcf.pokedex.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pokemons")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @ManyToMany
    @JoinTable(
            name = "pokemon_tipo", // Nome da tabela de junção (ex: pokemon_tipo)
            joinColumns = @JoinColumn(name = "pokemon_id"), // Coluna que referencia Pokemon (esta entidade)
            inverseJoinColumns = @JoinColumn(name = "tipo_id") // Coluna que referencia TipoPokemon (a outra entidade)
    )
    private Set<TipoPokemon> tipo;

    private Float altura;
    private Float peso;
}
