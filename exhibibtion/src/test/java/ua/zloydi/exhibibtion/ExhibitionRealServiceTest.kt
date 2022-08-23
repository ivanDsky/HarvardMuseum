package ua.zloydi.exhibibtion

import androidx.paging.PagingSource
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.*
import ua.zloydi.exhibibtion.data.ExhibitionPagingSource
import ua.zloydi.exhibibtion.data.ExhibitionRemoteDataSource
import ua.zloydi.exhibibtion.data.ExhibitionService
import ua.zloydi.exhibibtion.filters.StatusFilter
import ua.zloydi.exhibibtion.filters.toFilter
import ua.zloydi.exhibibtion.models.ExhibitionDetail
import ua.zloydi.exhibibtion.models.ImagesItem
import ua.zloydi.exhibibtion.models.VenuesItem
import ua.zloydi.test.retrofit.RetrofitTest
import java.text.SimpleDateFormat

class ExhibitionRealServiceTest {
	private val retrofit =
		RetrofitTest.getRetrofit(ua.zloydi.retrofit.BuildConfig.BASE_URL.toHttpUrl())
	private val service = retrofit.create(ExhibitionService::class.java)
	private val remoteDataSource = ExhibitionRemoteDataSource(service)
	
	@Test
	fun testResponseReceived() {
		val testSubscriber = remoteDataSource.getExhibitions(emptyList()).test()
		
		
		testSubscriber.assertNoErrors()
		testSubscriber.assertComplete()
		testSubscriber.assertValue { query ->
			expectThat(query.info) {
				get { page }.isEqualTo(1)
				get { pages }.isGreaterThanOrEqualTo(1)
			}
			expectThat(query.records) {
				isNotEmpty()
				all { get { title }.isNotEmpty() }
				all { get { exhibitionId }.isGreaterThan(0) }
			}
			true
		}
	}
	
	@Test
	fun testUpcomingExhibitions() {
		val testSubscriber =
			ExhibitionPagingSource(remoteDataSource, listOf(StatusFilter.Status.Upcoming.toFilter()))
				.loadSingle(PagingSource.LoadParams.Refresh(null,20,false))
				.test()
				.await()
		
		testSubscriber.assertNoErrors()
		testSubscriber.assertComplete()
		
		val currentTime = System.currentTimeMillis()
		val dateParser = SimpleDateFormat("yyyy-MM-dd")
		
		testSubscriber.assertValue {
			assert(it is PagingSource.LoadResult.Page)
			it as PagingSource.LoadResult.Page
			expectThat(it.data){
				isNotEmpty()
				all { get { title }.isNotEmpty() }
				all {
					get { beginDate }.isNotNull()
					get { dateParser.parse(beginDate).time }.isGreaterThan(currentTime)
				}
			}
			true
		}
	}
	@Test
	fun testCurrentExhibitions() {
		val testSubscriber =
			ExhibitionPagingSource(remoteDataSource, listOf(StatusFilter.Status.Current.toFilter()))
				.loadSingle(PagingSource.LoadParams.Refresh(null,20,false))
				.test()
				.await()

		testSubscriber.assertNoErrors()
		testSubscriber.assertComplete()

		val currentTime = System.currentTimeMillis()
		val dateParser = SimpleDateFormat("yyyy-MM-dd")

		testSubscriber.assertValue {
			assert(it is PagingSource.LoadResult.Page)
			it as PagingSource.LoadResult.Page
			expectThat(it.data){
				isNotEmpty()
				all { get { title }.isNotEmpty() }
				all {
					get { beginDate }.isNotNull()
					get { dateParser.parse(beginDate).time }.isLessThan(currentTime)
				}
				all {
					get { endDate }.isNotNull()
					get { dateParser.parse(endDate).time }.isGreaterThan(currentTime)
				}
			}
			true
		}
	}

	@Test
	fun testDetailReceived() {
		val expected = ExhibitionDetail(
			exhibitionId = 249,
			title = "David Smith: \"This work is my identity\"",
			beginDate = "1995-06-03",
			endDate = "1996-05-05",
			primaryImageUrl = "https://nrs.harvard.edu/urn-3:huam:GS04997_dynmc",
			shortDescription = null,
			description = null,
			images = listOf(
				ImagesItem(
					"2010-11-17",
					null,
					400027,
					20480582,
					null,
					null,
					"https://nrs.harvard.edu/urn-3:huam:GS04997_dynmc"
				)
			),
			venues = listOf(
				VenuesItem(
					10615,
					"1995-06-03",
					"1996-05-05",
					"Harvard University Art Museums",
					null,
					"MA",
					"Cambridge",
					"32 Quincy Street"
				)
			),
			publications = null
		)
		
		val testSubscriber = service.getExhibition(249).test()
		
		testSubscriber.assertNoErrors()
		testSubscriber.assertComplete()
		testSubscriber.assertResult(expected)
	}
}