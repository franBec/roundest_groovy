package dev.pollito.roundest_groovy.service

import dev.pollito.roundest_groovy.model.Pokemon
import dev.pollito.roundest_groovy.model.Pokemons
import org.springframework.data.domain.PageRequest

interface PokemonService {
    Pokemons findAll(String name, PageRequest pageRequest, Boolean random)
    Pokemon findById(Long id)
    Void incrementPokemonVotes(Long id)
}