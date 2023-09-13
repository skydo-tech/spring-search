package com.sipios.springsearch.configuration

import com.sipios.springsearch.SpecificationsBuilder
import com.sipios.springsearch.anotation.SearchSpec
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.domain.Specification

class SearchSpecificationResolManually {

    companion object {
        private val logger = LoggerFactory.getLogger(SearchSpecificationResolver::class.java)

        fun <T> buildSpecification(specClass: Class<T>, search: String?, searchSpecAnnotation: SearchSpec): Specification<T>? {
            logger.debug("Building specification for class {}", specClass)
            logger.debug("Search value found is {}", search)
            if (search == null || search.isEmpty()) {
                return null
            }

            SearchSpec(searchParam = "search", )
            val specBuilder = SpecificationsBuilder<T>(searchSpecAnnotation)

            return specBuilder.withSearch(search).build()
        }
    }
}
