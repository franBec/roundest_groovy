package dev.pollito.roundest_groovy.service

import dev.pollito.roundest_groovy.entity.Pokemon
import dev.pollito.roundest_groovy.mapper.PokemonModelMapper
import dev.pollito.roundest_groovy.repository.PokemonRepository
import dev.pollito.roundest_groovy.service.impl.PokemonServiceImpl
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

class PokemonServiceSpec extends Specification {

    PokemonServiceImpl pokemonService
    PokemonRepository pokemonRepository = Mock()
    PokemonModelMapper pokemonModelMapper = Spy(Mappers.getMapper(PokemonModelMapper))

    def setup() {
        pokemonService = new PokemonServiceImpl(pokemonRepository, pokemonModelMapper)
    }

    def "when findAll not random then return pokemons"() {
        given: "a mocked repository returning an empty page"
        pokemonRepository.findAll(_ as PageRequest) >>
                new PageImpl<>([], PageRequest.of(0, 10), 0)

        when: "calling findAll with random set to false"
        def result = pokemonService.findAll(Mock(PageRequest), false)

        then: "result is not null"
        result != null
    }

    def "when findAll random then return pokemons"() {
        given: "a mocked repository returning an empty list for random ids"
        pokemonRepository.findByIds(_ as List) >> []

        and: "a mocked PageRequest"
        def pageRequest = Mock(PageRequest) {
            getPageSize() >> 2
        }

        when: "calling findAll with random set to true"
        def result = pokemonService.findAll(pageRequest, true)

        then: "result is not null"
        result != null
    }

    def "when incrementPokemonVotes then return void and increment votes"() {
        given: "a Pokemon entity with initial votes"
        def pokemon = new Pokemon(votes: 0)
        def pokemonInitialVotes = pokemon.votes

        and: "a mocked repository behavior"
        pokemonRepository.findById(_ as Long) >> Optional.of(pokemon)
        pokemonRepository.save(_ as Pokemon) >> pokemon

        when: "incrementing Pokemon votes"
        def result = pokemonService.incrementPokemonVotes(1L)

        then: "the service returns null"
        result == null

        and: "the votes are incremented by 1"
        pokemon.votes == pokemonInitialVotes + 1
    }
}
