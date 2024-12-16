package dev.pollito.roundest_groovy.util

import spock.lang.Specification
import spock.lang.Unroll

class RandomUtilsSpec extends Specification {

    def "generateRandomIds should generate a list of unique IDs with specified count"() {
        given:
        int count = 5

        when:
        List<Long> randomIds = RandomUtils.generateRandomIds(count)

        then:
        randomIds.size() == count
        randomIds.unique().size() == count
        randomIds.every { it >= 1 && it <= 151 }
    }

    def "generateRandomIds should throw IllegalArgumentException when count exceeds MAX_COUNT"() {
        given:
        int count = 11

        when:
        RandomUtils.generateRandomIds(count)

        then:
        thrown(IllegalArgumentException)
    }

    def "generateRandomIds should handle edge case with count equal to MAX_COUNT"() {
        given:
        int count = 10

        when:
        List<Long> randomIds = RandomUtils.generateRandomIds(count)

        then:
        randomIds.size() == count
        randomIds.unique().size() == count
        randomIds.every { it >= 1 && it <= 151 }
    }

    def "generateRandomIds should handle edge case with count of 1"() {
        given:
        int count = 1

        when:
        List<Long> randomIds = RandomUtils.generateRandomIds(count)

        then:
        randomIds.size() == count
        randomIds.unique().size() == count
        randomIds.every { it >= 1 && it <= 151 }
    }

    @Unroll
    def "generateRandomIds should generate a correct list for various counts within valid range"() {
        when:
        List<Long> randomIds = RandomUtils.generateRandomIds(count as int)

        then:
        randomIds.size() == count
        randomIds.unique().size() == count
        randomIds.every { it >= 1 && it <= 151 }

        where:
        count << ([2, 3, 7, 9])
    }

    def "generateRandomIds should return an empty list when count is 0"() {
        given:
        int count = 0

        when:
        List<Long> randomIds = RandomUtils.generateRandomIds(count)

        then:
        randomIds.isEmpty()
    }
}
