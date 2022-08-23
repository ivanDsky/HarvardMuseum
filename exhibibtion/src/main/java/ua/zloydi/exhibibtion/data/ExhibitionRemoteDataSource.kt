package ua.zloydi.exhibibtion.data

import io.reactivex.rxjava3.core.Single
import ua.zloydi.exhibibtion.filters.Filters
import ua.zloydi.exhibibtion.models.ExhibitionDetail
import ua.zloydi.exhibibtion.models.ExhibitionQuery
import ua.zloydi.exhibibtion.modules.ExhibitionScope
import javax.inject.Inject

@ExhibitionScope
internal class ExhibitionRemoteDataSource @Inject constructor(private val service: ExhibitionService) :
	ExhibitionRepository {
	override fun getExhibitions(filters: Filters): Single<ExhibitionQuery> =
		service.getFilteredExhibitions(filters.associate { it.toQuery() })
	
	override fun getExhibition(exhibitionId: Int): Single<ExhibitionDetail> =
		service.getExhibition(exhibitionId)
}