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

	def "when findAll then return pokemons"() {
		given: "a mocked repository returning a page"
		pokemonRepository
				.findAll(_ as PageRequest) >> new PageImpl<>([], PageRequest.of(0, 10), 0)

		when: "calling findAll with no name and random false"
		def result = pokemonService.findAll(
				null,
				0,
				10,
				Collections.emptyList(),
				false
				)

		then: "result is not null"
		result != null
	}

	def "when findAll random then return pokemons"() {
		given: "a mocked repository returning a list for random ids"
		pokemonRepository.findByIds(_ as List) >> []

		when: "calling findAll with random set to true"
		def result = pokemonService.findAll(
				null,
				0,
				2,
				Collections.emptyList(),
				true
				)

		then: "result is not null"
		result != null
	}

	def "when findAll with name then return pokemons"(){
		given: "a mocked repository returning a page"
		pokemonRepository
				.findByNameContainingIgnoreCase(_ as String, _ as PageRequest) >> new PageImpl<>([], PageRequest.of(0, 10), 0)

		when: "calling findAll with a name"
		def result = pokemonService.findAll(
				"abra",
				0,
				10,
				Collections.emptyList(),
				false
				)

		then: "result is not null"
		result != null
	}

	def "when findById then return Pokemon"(){
		given: "a mocked repository behaviour"
		pokemonRepository.findById(_ as Long) >> Optional.of(new Pokemon())

		when: "finding a pokemon"
		def result = pokemonService.findById(1L)

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
