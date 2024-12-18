package dev.pollito.roundest_groovy.repository

import dev.pollito.roundest_groovy.entity.Pokemon
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PokemonRepository extends JpaRepository<Pokemon, Long> {

	@Query("SELECT p FROM Pokemon p WHERE p.id IN :ids")
	List<Pokemon> findByIds(@Param("ids") List<Long> ids)

	Page<Pokemon> findByNameContainingIgnoreCase(String name, Pageable pageable)
}
