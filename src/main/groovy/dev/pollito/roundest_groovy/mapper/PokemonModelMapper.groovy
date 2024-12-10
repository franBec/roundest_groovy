package dev.pollito.roundest_groovy.mapper

import dev.pollito.roundest_groovy.entity.Pokemon
import dev.pollito.roundest_groovy.model.Pokemons
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.springframework.data.domain.Page

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface PokemonModelMapper {
    dev.pollito.roundest_groovy.model.Pokemon map(Pokemon pokemon)
    Pokemons map(Page<Pokemon> pokemonPage)
}