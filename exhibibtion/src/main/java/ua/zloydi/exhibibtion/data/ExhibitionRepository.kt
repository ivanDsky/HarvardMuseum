package ua.zloydi.exhibibtion.data

import io.reactivex.rxjava3.core.Single
import ua.zloydi.exhibibtion.filters.Filters
import ua.zloydi.exhibibtion.models.ExhibitionDetail
import ua.zloydi.exhibibtion.models.ExhibitionQuery

internal interface ExhibitionItemRepository{
	fun getExhibitions(filters: Filters): Single<ExhibitionQuery>
}
internal interface ExhibitionDetailRepository{
	fun getExhibition(exhibitionId: Int): Single<ExhibitionDetail>
}

internal interface ExhibitionRepository : ExhibitionItemRepository, ExhibitionDetailRepository