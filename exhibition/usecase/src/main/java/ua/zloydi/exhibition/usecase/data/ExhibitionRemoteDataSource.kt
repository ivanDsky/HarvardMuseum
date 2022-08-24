package ua.zloydi.exhibition.usecase.data

import io.reactivex.rxjava3.core.Single
import ua.zloydi.exhibition.usecase.filters.Filters
import ua.zloydi.exhibition.usecase.models.ExhibitionDetail
import ua.zloydi.exhibition.usecase.models.ExhibitionQuery
import ua.zloydi.exhibition.usecase.modules.ExhibitionScope
import javax.inject.Inject

@ExhibitionScope
public class ExhibitionRemoteDataSource @Inject constructor(private val service: ExhibitionService) :
	ExhibitionRepository {
	override fun getExhibitions(filters: Filters): Single<ExhibitionQuery> =
		service.getFilteredExhibitions(filters.associate { it.toQuery() })
	
	override fun getExhibition(exhibitionId: Int): Single<ExhibitionDetail> =
		service.getExhibition(exhibitionId)
}