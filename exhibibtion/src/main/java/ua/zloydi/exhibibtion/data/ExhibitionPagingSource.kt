package ua.zloydi.exhibibtion.data

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.zloydi.exhibibtion.filters.ExhibitionFilter
import ua.zloydi.exhibibtion.filters.Filters
import ua.zloydi.exhibibtion.models.ExhibitionItem
import ua.zloydi.exhibibtion.models.ExhibitionQuery

private class PageFilter(page: Int) : ExhibitionFilter<Int>(){
	override val name: String = "page"
	override val value = page
}

private class SizeFilter(size: Int) : ExhibitionFilter<Int>(){
	override val name: String = "size"
	override val value = size
}
internal class ExhibitionPagingSource(
	private val itemRepository: ExhibitionItemRepository,
	private val filters: Filters
) : RxPagingSource<Int, ExhibitionItem>() {
	override fun getRefreshKey(state: PagingState<Int, ExhibitionItem>):Int? {
		val pos = state.anchorPosition ?: return null
		val page = state.closestPageToPosition(pos) ?: return null
		if (page.prevKey != null) return page.prevKey!! + 1
		if (page.nextKey != null) return page.nextKey!! - 1
		return null
	}
	
	override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ExhibitionItem>> {
		val nextPage = params.key ?: 1
		return itemRepository.getExhibitions(
			filters + PageFilter(nextPage) + SizeFilter(params.loadSize)
		)
			.subscribeOn(Schedulers.io())
			.map { it.toLoadResult() }
			.onErrorReturn { LoadResult.Error(it) }
	}
	
	private fun ExhibitionQuery.toLoadResult(): LoadResult<Int, ExhibitionItem> = LoadResult.Page(
		data = records,
		prevKey = if(info.page == 1) null else info.page - 1,
		nextKey = if (info.page == info.pages) null else info.page + 1
	)
}