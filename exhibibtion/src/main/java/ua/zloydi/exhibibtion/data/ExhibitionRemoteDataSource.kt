package ua.zloydi.exhibibtion.data

import io.reactivex.rxjava3.core.Single
import ua.zloydi.exhibibtion.models.ExhibitionDetail
import ua.zloydi.exhibibtion.models.ExhibitionQuery
import ua.zloydi.exhibibtion.modules.ExhibitionScope
import javax.inject.Inject

@ExhibitionScope
internal class ExhibitionRemoteDataSource @Inject constructor(private val service: ExhibitionService) :
	ExhibitionRepository {
	override fun getUpcomingExhibitions(): Single<ExhibitionQuery> =
		service.getUpcomingExhibitions()
	
	override fun getCurrentExhibitions(): Single<ExhibitionQuery> = service.getCurrentExhibitions()
	
	override fun getExhibition(exhibitionId: Int): Single<ExhibitionDetail> =
		service.getExhibition(exhibitionId)
}