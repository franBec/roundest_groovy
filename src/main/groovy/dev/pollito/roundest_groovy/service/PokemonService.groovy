package dev.pollito.roundest_groovy.service

import dev.pollito.roundest_groovy.model.Pokemons
import org.springframework.data.domain.PageRequest

interface PokemonService {
    Pokemons findAll(PageRequest pageRequest, Boolean random)

    Void incrementPokemonVotes(Long id)
}