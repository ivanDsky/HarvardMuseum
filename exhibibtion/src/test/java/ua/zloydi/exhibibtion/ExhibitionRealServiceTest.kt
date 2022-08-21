package ua.zloydi.exhibibtion

import strikt.api.*
import strikt.assertions.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Test
import strikt.api.expect
import ua.zloydi.exhibibtion.data.ExhibitionService
import ua.zloydi.exhibibtion.models.*
import ua.zloydi.test.retrofit.RetrofitTest
import java.net.HttpURLConnection

class ExhibitionRealServiceTest {
	private val retrofit = RetrofitTest.getRetrofit(ua.zloydi.retrofit.BuildConfig.BASE_URL.toHttpUrl())
	private val service = retrofit.create(ExhibitionService::class.java)
	
	@Test
	fun testResponseReceived() {
		val testSubscriber = service.getAllExhibitions().test()
		
		
		testSubscriber.assertNoErrors()
		testSubscriber.assertComplete()
		testSubscriber.assertValue { query ->
			expectThat(query.info){
				get { page }.isEqualTo(1)
				get { pages }.isGreaterThanOrEqualTo(1)
			}
			expectThat(query.records){
				isNotEmpty()
				all { get { title }.isNotEmpty() }
				all { get { exhibitionId }.isGreaterThan(0) }
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