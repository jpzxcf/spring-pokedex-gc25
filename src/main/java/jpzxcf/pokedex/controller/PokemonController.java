package jpzxcf.pokedex.controller;

import jpzxcf.pokedex.DTO.PokemonDTO;
import jpzxcf.pokedex.DTO.PokemonInputDTO;
import jpzxcf.pokedex.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> findAll() {
        List<PokemonDTO> pokemons = pokemonService.findAll();
        return ResponseEntity.ok(pokemons);
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> create(@RequestBody PokemonInputDTO inputDTO) {
        PokemonDTO createdPokemon = pokemonService.create(inputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPokemon);
    }

    // ... outros endpoints (GET por ID, PUT, DELETE)
}