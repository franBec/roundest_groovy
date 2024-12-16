package dev.pollito.roundest_groovy.service

import dev.pollito.roundest_groovy.model.Pokemon
import dev.pollito.roundest_groovy.model.Pokemons

interface PokemonService {
    Pokemons findAll(
            String name,
            Integer pageNumber,
            Integer pageSize,
            List<String> pageSort,
            Boolean random
    )
    Pokemon findById(Long id)
    Void incrementPokemonVotes(Long id)
}