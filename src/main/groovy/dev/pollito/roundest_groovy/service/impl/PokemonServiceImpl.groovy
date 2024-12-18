package dev.pollito.roundest_groovy.service.impl

import dev.pollito.roundest_groovy.mapper.PokemonModelMapper
import dev.pollito.roundest_groovy.model.Pokemon
import dev.pollito.roundest_groovy.model.Pokemons
import dev.pollito.roundest_groovy.repository.PokemonRepository
import dev.pollito.roundest_groovy.service.PokemonService
import dev.pollito.roundest_groovy.util.PageableUtils
import dev.pollito.roundest_groovy.util.RandomUtils
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class PokemonServiceImpl implements PokemonService {
	private final PokemonRepository pokemonRepository
	private final PokemonModelMapper pokemonModelMapper

	PokemonServiceImpl(PokemonRepository pokemonRepository, PokemonModelMapper pokemonModelMapper) {
		this.pokemonRepository = pokemonRepository
		this.pokemonModelMapper = pokemonModelMapper
	}

	@Override
	Pokemons findAll(
			String name,
			Integer pageNumber,
			Integer pageSize,
			List<String> pageSort,
			Boolean random
	) {
		if (random) {
			return getRandomPokemons(pageSize)
		}

		def pageable = PageableUtils.createPageable(
				pageNumber,
				pageSize,
				pageSort
				)

		if (StringUtils.hasText(name)) {
			return pokemonModelMapper.map(pokemonRepository.findByNameContainingIgnoreCase(name, pageable))
		}
		pokemonModelMapper.map(pokemonRepository.findAll(pageable))
	}

	@Override
	Pokemon findById(Long id) {
		pokemonModelMapper.map(pokemonRepository.findById(id).orElseThrow())
	}

	@Override
	Void incrementPokemonVotes(Long id) {
		def pokemon = pokemonRepository.findById(id).orElseThrow()
		pokemon.votes += 1
		pokemonRepository.save pokemon
		return null
	}

	private Pokemons getRandomPokemons(int size) {
		def pokemons = pokemonRepository.findByIds(RandomUtils.generateRandomIds(size))
		pokemonModelMapper.map(
				new PageImpl<>(pokemons, PageRequest.of(0, size), pokemons.size())
				)
	}
}
