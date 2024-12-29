package dev.pollito.roundest_groovy.util

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class PageableUtils {

	private PageableUtils() {}

	static Pageable createPageable(int page, int size, List<String> sort) {
		Sort combinedSort = Sort.unsorted()
		boolean hasIdSort = false

		sort.each { sortField ->
			String[] sortParams = sortField.split(":")
			Sort.Direction direction = (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1]))
					? Sort.Direction.DESC
					: Sort.Direction.ASC

			if ("id".equalsIgnoreCase(sortParams[0])) {
				hasIdSort = true
			}

			combinedSort = combinedSort & Sort.by(direction, sortParams[0])
		}

		if (!hasIdSort) {
			combinedSort = combinedSort & Sort.by(Sort.Direction.ASC, "id")
		}

		PageRequest.of(page, size, combinedSort)
	}
}
