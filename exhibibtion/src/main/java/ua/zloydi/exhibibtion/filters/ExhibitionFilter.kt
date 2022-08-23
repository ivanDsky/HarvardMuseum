package ua.zloydi.exhibibtion.filters

typealias Filters = List<ExhibitionFilter<*>>

abstract class ExhibitionFilter<T>{
	abstract val name: String
	abstract val value: T
	open fun toQuery(): Pair<String, String> = name to value.toString()
}

class StatusFilter(status: Status): ExhibitionFilter<StatusFilter.Status>(){
	enum class Status{ Current, Past, Upcoming }
	
	override val name = "status"
	override val value = status
	
	override fun toQuery() = name to value.name.lowercase()
}

fun StatusFilter.Status.toFilter() = StatusFilter(this)