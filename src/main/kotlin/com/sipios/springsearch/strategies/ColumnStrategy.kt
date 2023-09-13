package com.sipios.springsearch.strategies

import com.sipios.springsearch.SearchOperation
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import kotlin.reflect.KClass

class ColumnStrategy : ParsingStrategy {
    override fun buildPredicate(
        builder: CriteriaBuilder,
        path: Path<*>,
        fieldName: String,
        ops: SearchOperation?,
        value: Any?
    ): Predicate? {
        val cleanedValue = (value as String).replace("#", "")
        val valToCompare = path.get<Comparable<Any>>(cleanedValue)
        return when (ops) {
            SearchOperation.GREATER_THAN -> builder.greaterThan(path.get(fieldName), valToCompare)
            SearchOperation.LESS_THAN -> builder.lessThan(path.get(fieldName), valToCompare)
            SearchOperation.EQUALS -> builder.equal(path.get<Any>(fieldName), valToCompare)
            SearchOperation.NOT_EQUALS -> builder.notEqual(path.get<Any>(fieldName), valToCompare)
            else -> throw Exception("Search operation $ops is not supported for this Column to Column comparison")
        }
    }

    override fun parse(value: String?, fieldClass: KClass<out Any>): Any? {
        return value
    }
}
