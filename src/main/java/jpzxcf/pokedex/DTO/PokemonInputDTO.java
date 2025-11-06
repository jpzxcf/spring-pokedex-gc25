package jpzxcf.pokedex.DTO;

import java.util.Set;

public record PokemonInputDTO(
        String nome,
        Set<String> nomesTipos, // Recebe uma lista de nomes de tipo (ex: "FOGO", "AGUA")
        Float altura,
        Float peso
) {}