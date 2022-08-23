package ua.zloydi.exhibibtion.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ua.zloydi.exhibibtion.models.ExhibitionDetail
import ua.zloydi.exhibibtion.models.ExhibitionQuery

internal interface ExhibitionService {
	@GET("/exhibition/{id}")
	fun getExhibition(
		@Path("id") exhibitionId: Int
	): Single<ExhibitionDetail>
	
	@GET("/exhibition?status=upcoming")
	fun getUpcomingExhibitions(): Single<ExhibitionQuery>
	
	@GET("/exhibition?status=current")
	fun getCurrentExhibitions(): Single<ExhibitionQuery>
}