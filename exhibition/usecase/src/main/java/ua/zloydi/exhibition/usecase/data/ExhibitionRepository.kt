package ua.zloydi.exhibition.usecase.data

import io.reactivex.rxjava3.core.Single
import ua.zloydi.exhibition.usecase.filters.Filters
import ua.zloydi.exhibition.usecase.models.ExhibitionDetail
import ua.zloydi.exhibition.usecase.models.ExhibitionQuery

public interface ExhibitionItemRepository{
	fun getExhibitions(filters: Filters): Single<ExhibitionQuery>
}
public interface ExhibitionDetailRepository{
	fun getExhibition(exhibitionId: Int): Single<ExhibitionDetail>
}

internal interface ExhibitionRepository : ExhibitionItemRepository, ExhibitionDetailRepository