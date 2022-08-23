package ua.zloydi.exhibibtion.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ua.zloydi.exhibibtion.models.ExhibitionDetail
import ua.zloydi.exhibibtion.models.ExhibitionQuery

internal interface ExhibitionService {
	@GET("/exhibition")
	fun getFilteredExhibitions(@QueryMap filters: Map<String, String>): Single<ExhibitionQuery>
	
	@GET("/exhibition/{id}")
	fun getExhibition(@Path("id") exhibitionId: Int): Single<ExhibitionDetail>
}