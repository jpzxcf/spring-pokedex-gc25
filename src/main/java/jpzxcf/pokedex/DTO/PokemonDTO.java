package jpzxcf.pokedex.DTO;

import jpzxcf.pokedex.entities.TipoPokemon;
import java.util.Set;

public record PokemonDTO(
        Long id,
        String nome,
        Set<TipoPokemonDTO> tipo,
        Float altura,
        Float peso
) {}