package dev.pollito.roundest_groovy.controller

import dev.pollito.roundest_groovy.model.Pokemons
import dev.pollito.roundest_groovy.model.PokemonSortProperty
import dev.pollito.roundest_groovy.model.SortDirection
import dev.pollito.roundest_groovy.service.PokemonService
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class PokemonsControllerSpec extends Specification {
    PokemonsController pokemonsController
    PokemonService pokemonService = Mock()

    def setup() {
        pokemonsController = new PokemonsController(pokemonService)
    }

    def "when findAll then return OK"() {
        given: "a mocked service interaction"
        pokemonService.findAll(_ as PageRequest, _ as Boolean) >> Mock(Pokemons)

        when: "calling findAll on the controller"
        ResponseEntity<Pokemons> response = pokemonsController.findAll(
                0, 10, PokemonSortProperty.ID, SortDirection.ASC, true
        )

        then: "the response status is OK and the body is not null"
        response.statusCode == HttpStatus.OK
        response.body != null
    }

    def "when incrementPokemonVotes then return NO_CONTENT"() {
        given: "a mocked service interaction"
        pokemonService.incrementPokemonVotes(_ as Long) >> { /* do nothing */ }

        when: "calling incrementPokemonVotes on the controller"
        ResponseEntity<Void> response = pokemonsController.incrementPokemonVotes(1L)

        then: "the response status is NO_CONTENT and the body is null"
        response.statusCode == HttpStatus.NO_CONTENT
        response.body == null

        and: "the service interaction is verified"
        1 * pokemonService.incrementPokemonVotes(_ as Long)
    }
}