package ua.zloydi.exhibibtion.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ua.zloydi.exhibibtion.models.ExhibitionDetail
import ua.zloydi.exhibibtion.models.ExhibitionQuery

internal interface ExhibitionRepository {
	//TODO rewrite with pagination
	fun getAllExhibitions(): Observable<ExhibitionQuery>
	
	fun getExhibition(exhibitionId: Int): Single<ExhibitionDetail>
}