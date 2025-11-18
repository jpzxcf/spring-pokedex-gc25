package jpzxcf.pokedex.service;

import jpzxcf.pokedex.entities.Pokemon;
import jpzxcf.pokedex.repository.PokemonRepository; // Você precisará criar esta interface
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional
    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Transactional(readOnly = true)
    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pokemon> findById(Long id) {
        return pokemonRepository.findById(id);
    }

    @Transactional
    public Pokemon update(Long id, Pokemon pokemonDetails) {
        Pokemon existingPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokémon não encontrado com ID: " + id));
        existingPokemon.setNome(pokemonDetails.getNome());
        existingPokemon.setAltura(pokemonDetails.getAltura());
        existingPokemon.setPeso(pokemonDetails.getPeso());
        existingPokemon.setTipo(pokemonDetails.getTipo());

        return pokemonRepository.save(existingPokemon);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!pokemonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pokémon não encontrado com ID: " + id);
        }
        pokemonRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Pokemon> findPokemonsByType(String typeName) {
        return List.of();
    }
}

class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}