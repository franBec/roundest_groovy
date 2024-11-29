package dev.pollito.roundest_groovy.converter

import dev.pollito.roundest_groovy.model.PokemonSortProperty
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PokemonSortPropertyConverter implements Converter<String, PokemonSortProperty> {

    @Override
    PokemonSortProperty convert(String source) {
        PokemonSortProperty.fromValue(source)
    }
}