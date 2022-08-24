package ua.zloydi.exhibition.usecase.filters

import ua.zloydi.search.Filter

typealias Filters = List<ExhibitionFilter<*>>

abstract class ExhibitionFilter<T> : Filter<T>()

public class StatusFilter(status: Status): ExhibitionFilter<StatusFilter.Status>(){
	enum class Status{ Current, Past, Upcoming }
	
	override val name = "status"
	override val value = status
	
	override fun toQuery() = name to value.name.lowercase()
}

fun StatusFilter.Status.toFilter() = StatusFilter(this)