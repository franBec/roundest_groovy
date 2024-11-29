package dev.pollito.roundest_groovy.controller

import dev.pollito.roundest_groovy.api.PokemonsApi
import dev.pollito.roundest_groovy.model.PokemonSortProperty
import dev.pollito.roundest_groovy.model.Pokemons
import dev.pollito.roundest_groovy.model.SortDirection
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonsController implements PokemonsApi {
    @Override
    ResponseEntity<Pokemons> findAll(
            Integer pageNumber,
            Integer pageSize,
            PokemonSortProperty sortProperty,
            SortDirection sortDirection,
            Boolean random
    ) {
        super.findAll(pageNumber, pageSize, sortProperty, sortDirection, random)
    }

    @Override
    ResponseEntity<Void> incrementPokemonVotes(Long id) {
        return super.incrementPokemonVotes(id)
    }
}
