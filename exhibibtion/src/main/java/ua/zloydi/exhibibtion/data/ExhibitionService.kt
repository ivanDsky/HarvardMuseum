package ua.zloydi.exhibibtion.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ua.zloydi.exhibibtion.models.ExhibitionDetail
import ua.zloydi.exhibibtion.models.ExhibitionQuery
import ua.zloydi.retrofit.BuildConfig

internal interface ExhibitionService {
	@GET("/exhibition")
	fun getAllExhibitions(): Observable<ExhibitionQuery>
	
	@GET("/exhibition/{id}")
	fun getExhibition(
		@Path("id") exhibitionId: Int
	): Single<ExhibitionDetail>
	
	@GET("/exhibition")
	fun getPopularExhibitions(): Single<ExhibitionQuery>
	
	@GET("/exhibition")
	fun getCurrentExhibitions(): Single<ExhibitionQuery>
}