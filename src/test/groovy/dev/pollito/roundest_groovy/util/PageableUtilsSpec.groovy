package dev.pollito.roundest_groovy.util

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import spock.lang.Specification
import spock.lang.Unroll

class PageableUtilsSpec extends Specification {

    @Unroll
    def "createPageable should return correct Pageable with page: #page, size: #size, sort: #sort"() {
        when:
        Pageable pageable = PageableUtils.createPageable(page, size, sort)

        then:
        pageable.pageNumber == page
        pageable.pageSize == size
        pageable.sort == expectedSort

        where:
        page | size | sort                          || expectedSort
        0    | 10   | []                            || Sort.by(Sort.Direction.ASC, "id")
        1    | 5    | ["name:asc"]                  || Sort.by(Sort.Direction.ASC, "name") & Sort.by(Sort.Direction.ASC, "id")
        2    | 15   | ["name:desc", "votes:asc"]    || (Sort.by(Sort.Direction.DESC, "name") & Sort.by(Sort.Direction.ASC, "votes")) & Sort.by(Sort.Direction.ASC, "id")
        0    | 20   | ["id:desc"]                   || Sort.by(Sort.Direction.DESC, "id")
        3    | 10   | ["name:desc", "id:asc"]       || Sort.by(Sort.Direction.DESC, "name") & Sort.by(Sort.Direction.ASC, "id")
    }

    def "createPageable should handle invalid sort format gracefully"() {
        when:
        Pageable pageable = PageableUtils.createPageable(0, 10, ["invalid_sort_format"])

        then:
        pageable.pageNumber == 0
        pageable.pageSize == 10
        pageable.sort == (Sort.by(Sort.Direction.ASC, "invalid_sort_format") & Sort.by(Sort.Direction.ASC, "id"))
    }

    def "createPageable should add default sort by 'id' if not provided"() {
        when:
        Pageable pageable = PageableUtils.createPageable(0, 10, ["name:asc"])

        then:
        pageable.pageNumber == 0
        pageable.pageSize == 10
        pageable.sort == (Sort.by(Sort.Direction.ASC, "name") & Sort.by(Sort.Direction.ASC, "id"))
    }

    def "createPageable should return unsorted Pageable when sort list is empty"() {
        when:
        Pageable pageable = PageableUtils.createPageable(0, 10, [])

        then:
        pageable.pageNumber == 0
        pageable.pageSize == 10
        pageable.sort == Sort.by(Sort.Direction.ASC, "id")
    }

    def "createPageable should handle null sort list by adding default 'id' sort"() {
        when:
        Pageable pageable = PageableUtils.createPageable(0, 10, null)

        then:
        pageable.pageNumber == 0
        pageable.pageSize == 10
        pageable.sort == Sort.by(Sort.Direction.ASC, "id")
    }
}
