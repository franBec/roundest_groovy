package dev.pollito.roundest_groovy.service.impl

import dev.pollito.roundest_groovy.mapper.PokemonModelMapper
import dev.pollito.roundest_groovy.model.Pokemons
import dev.pollito.roundest_groovy.repository.PokemonRepository
import dev.pollito.roundest_groovy.service.PokemonService
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PokemonServiceImpl implements PokemonService {
    private static final int POKEMON_ID_MIN = 1
    private static final int POKEMON_ID_MAX = 151
    private static final Random RANDOM = new Random()

    private final PokemonRepository pokemonRepository
    private final PokemonModelMapper pokemonModelMapper

    PokemonServiceImpl(PokemonRepository pokemonRepository, PokemonModelMapper pokemonModelMapper) {
        this.pokemonRepository = pokemonRepository
        this.pokemonModelMapper = pokemonModelMapper
    }

    @Override
    Pokemons findAll(PageRequest pageRequest, Boolean random) {
        if (random == true) {
            return getRandomPokemons(pageRequest.pageSize)
        }
        pokemonModelMapper.map(pokemonRepository.findAll(pageRequest))
    }

    private Pokemons getRandomPokemons(int size) {
        def pokemons = pokemonRepository.findByIds(generateRandomIds(size))
        pokemonModelMapper.map(
                new PageImpl<>(pokemons, PageRequest.of(0, size), pokemons.size())
        )
    }

    @Override
    Void incrementPokemonVotes(Long id) {
        def pokemon = pokemonRepository.findById(id).orElseThrow()
        pokemon.votes += 1
        pokemonRepository.save(pokemon)
        return null
    }

    private static List<Long> generateRandomIds(int count) {
        def ids = new HashSet<Long>()
        while (ids.size() < count) {
            ids << ((POKEMON_ID_MIN + RANDOM.nextInt(POKEMON_ID_MAX)) as Long)
        }
        ids as List<Long>
    }
}
