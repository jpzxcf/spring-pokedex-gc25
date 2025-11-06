package jpzxcf.pokedex.service;

import jpzxcf.pokedex.entities.Pokemon;
import jpzxcf.pokedex.entities.TipoPokemon;
import jpzxcf.pokedex.repository.PokemonRepository;
import jpzxcf.pokedex.repository.TipoPokemonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final TipoPokemonRepository tipoPokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository, TipoPokemonRepository tipoPokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.tipoPokemonRepository = tipoPokemonRepository;
    }
}