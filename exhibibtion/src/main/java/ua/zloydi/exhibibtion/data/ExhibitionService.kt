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
	fun getAllExhibitions(
		@Query("apikey") apiKey: String = BuildConfig.API_KEY
	): Observable<ExhibitionQuery>
	
	@GET("/exhibition/{id}")
	fun getExhibition(
		@Path("id") exhibitionId: Int,
		@Query("apikey") apiKey: String = BuildConfig.API_KEY
	): Single<ExhibitionDetail>
}