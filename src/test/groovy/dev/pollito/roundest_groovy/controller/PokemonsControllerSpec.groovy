package dev.pollito.roundest_groovy.controller

import dev.pollito.roundest_groovy.model.Pokemon
import dev.pollito.roundest_groovy.model.Pokemons
import dev.pollito.roundest_groovy.service.PokemonService
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import spock.lang.Specification

class PokemonsControllerSpec extends Specification {
    PokemonsController pokemonsController
    PokemonService pokemonService = Mock()

    def setup() {
        pokemonsController = new PokemonsController(pokemonService)
    }

    def "when findAll then return OK"() {
        given: "a mocked service interaction"
        pokemonService.findAll(
                _ as String,
                _ as Integer,
                _ as Integer,
                _ as List<String>,
                _ as Boolean
        ) >> Mock(Pokemons)

        when: "calling findAll on the controller"
        def response = pokemonsController.findAll(
                "Bulbasur",
                0,
                10,
                Collections.emptyList(),
                true
        )

        then: "the response status is OK and the body is not null"
        response.statusCode == HttpStatus.OK
        response.body != null
    }

    def "when findById then return OK"(){
        given: "a mocked service interaction"
        pokemonService.findById(_ as Long) >> Mock(Pokemon)

        when: "calling findById on the controller"
        def response = pokemonsController.findById(1L)

        then: "the response status is OK and the body is not null"
        response.statusCode == HttpStatus.OK
        response.body != null
    }

    def "when incrementPokemonVotes then return NO_CONTENT"() {
        given: "a mocked service interaction"
        pokemonService.incrementPokemonVotes(_ as Long) >> { /* do nothing */ }

        when: "calling incrementPokemonVotes on the controller"
        def response = pokemonsController.incrementPokemonVotes(1L)

        then: "the response status is NO_CONTENT and the body is null"
        response.statusCode == HttpStatus.NO_CONTENT
        response.body == null

        and: "the service interaction is verified"
        1 * pokemonService.incrementPokemonVotes(_ as Long)
    }
}
