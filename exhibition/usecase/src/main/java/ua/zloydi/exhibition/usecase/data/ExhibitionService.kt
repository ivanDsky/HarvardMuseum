package ua.zloydi.exhibition.usecase.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ua.zloydi.exhibition.usecase.models.ExhibitionDetail
import ua.zloydi.exhibition.usecase.models.ExhibitionQuery

public interface ExhibitionService {
	@GET("/exhibition")
	fun getFilteredExhibitions(@QueryMap filters: Map<String, String>): Single<ExhibitionQuery>
	
	@GET("/exhibition/{id}")
	fun getExhibition(@Path("id") exhibitionId: Int): Single<ExhibitionDetail>
}