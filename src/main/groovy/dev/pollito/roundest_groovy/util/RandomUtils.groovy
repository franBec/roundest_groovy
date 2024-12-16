package dev.pollito.roundest_groovy.util

class RandomUtils {

    private static final int POKEMON_ID_MIN = 1
    private static final int POKEMON_ID_MAX = 151
    private static final int MAX_COUNT = 10
    private static final Random RANDOM = new Random()

    private RandomUtils() {}

    static List<Long> generateRandomIds(int count) {
        if (count > MAX_COUNT) {
            throw new IllegalArgumentException("Count cannot exceed ${MAX_COUNT}")
        }

        def ids = new HashSet<Long>()
        while (ids.size() < count) {
            ids << ((POKEMON_ID_MIN + RANDOM.nextInt(POKEMON_ID_MAX)) as Long)
        }
        ids as List<Long>
    }
}