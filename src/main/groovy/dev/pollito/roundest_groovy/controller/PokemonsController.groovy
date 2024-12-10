package dev.pollito.roundest_groovy.controller

import dev.pollito.roundest_groovy.api.PokemonsApi
import dev.pollito.roundest_groovy.model.Pokemon
import dev.pollito.roundest_groovy.model.PokemonSortProperty
import dev.pollito.roundest_groovy.model.Pokemons
import dev.pollito.roundest_groovy.model.SortDirection
import dev.pollito.roundest_groovy.service.PokemonService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonsController implements PokemonsApi {

    private final PokemonService pokemonService

    PokemonsController(PokemonService pokemonService) {
        this.pokemonService = pokemonService
    }

    @Override
    ResponseEntity<Pokemons> findAll(
            String name,
            Integer pageNumber,
            Integer pageSize,
            PokemonSortProperty sortProperty,
            SortDirection sortDirection,
            Boolean random
    ) {
        ResponseEntity.ok(
                pokemonService.findAll(
                        name,
                        PageRequest.of(
                                pageNumber,
                                pageSize,
                                Sort.Direction.fromString(sortDirection.value),
                                sortProperty.value
                        ),
                        random
                )
        )
    }

    @Override
    ResponseEntity<Pokemon> findById(Long id) {
        ResponseEntity.ok pokemonService.findById(id)
    }

    @Override
    ResponseEntity<Void> incrementPokemonVotes(Long id) {
        new ResponseEntity<>(pokemonService.incrementPokemonVotes(id), HttpStatus.NO_CONTENT)
    }
}
