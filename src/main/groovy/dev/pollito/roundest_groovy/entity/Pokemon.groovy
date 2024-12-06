package dev.pollito.roundest_groovy.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokemons", schema = "dbo")
class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id

    @Column(name = "name", nullable = false, length = 10, updatable = false, unique = true)
    String name

    @Column(name = "sprite_url", nullable = false, updatable = false, unique = true)
    String spriteUrl

    @Column(name = "votes", nullable = false)
    int votes = 0
}