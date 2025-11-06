package jpzxcf.pokedex.service;

import jpzxcf.pokedex.DTO.PokemonDTO;
import jpzxcf.pokedex.DTO.PokemonInputDTO;
import jpzxcf.pokedex.DTO.TipoPokemonDTO;
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

    // --- Métodos de Conversão (Mappers) ---

    // Converte Entidade -> DTO (Para enviar ao cliente)
    private PokemonDTO toDTO(Pokemon pokemon) {
        Set<TipoPokemonDTO> tiposDTO = pokemon.getTipo().stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());

        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getNome(),
                tiposDTO,
                pokemon.getAltura(),
                pokemon.getPeso()
        );
    }

    // Converte TipoPokemon Entidade -> DTO
    private TipoPokemonDTO toDTO(TipoPokemon tipoPokemon) {
        return new TipoPokemonDTO(tipoPokemon.getId(), tipoPokemon.getNome());
    }

    // --- Métodos de Serviço ---

    @Transactional(readOnly = true)
    public List<PokemonDTO> findAll() {
        return pokemonRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PokemonDTO create(PokemonInputDTO inputDTO) {
        // 1. Busca os TiposPokemon existentes no banco de dados
        Set<TipoPokemon> tipos = new HashSet<>();
        for (String nomeTipoString : inputDTO.nomesTipos()) {
            try {
                TipoPokemon.NomeTipo nomeTipoEnum = TipoPokemon.NomeTipo.valueOf(nomeTipoString.toUpperCase());

                // --- Simulação de busca/criação de TipoPokemon (melhor seria buscar) ---
                TipoPokemon tipo = new TipoPokemon();
                tipo.setNome(nomeTipoEnum);
                tipos.add(tipo);
                // A implementação ideal requer o findByNome no Repository e que o BD já tenha os tipos.
                // --- Fim Simulação ---

            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Nome de Tipo inválido: " + nomeTipoString);
            }
        }

        // 2. Cria a entidade Pokemon
        Pokemon novoPokemon = new Pokemon();
        novoPokemon.setNome(inputDTO.nome());
        novoPokemon.setAltura(inputDTO.altura());
        novoPokemon.setPeso(inputDTO.peso());
        novoPokemon.setTipo(tipos);

        // 3. Salva no banco de dados
        Pokemon salvo = pokemonRepository.save(novoPokemon);

        // 4. Retorna o DTO
        return toDTO(salvo);
    }

    // ... outros métodos como findById, update, delete
}