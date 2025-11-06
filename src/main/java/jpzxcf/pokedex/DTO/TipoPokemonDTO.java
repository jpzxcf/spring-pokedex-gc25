package jpzxcf.pokedex.DTO;

import jpzxcf.pokedex.entities.TipoPokemon;

public record TipoPokemonDTO(
        Long id,
        TipoPokemon.NomeTipo nome
) {}