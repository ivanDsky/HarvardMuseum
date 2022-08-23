package ua.zloydi.exhibibtion

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import ua.zloydi.exhibibtion.data.ExhibitionService
import ua.zloydi.exhibibtion.models.*
import ua.zloydi.test.retrofit.RetrofitTest
import java.net.HttpURLConnection

class ExhibitionMockServiceTest {
	private val webServer = MockWebServer()
	private val retrofit = RetrofitTest.getRetrofit(webServer.url("/"))
	private val service = retrofit.create(ExhibitionService::class.java)
	
	@After
	fun close() {
		webServer.shutdown()
	}
	
	@Test
	fun testResponseReceived() {
		webServer.enqueue(
			MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(
				javaClass.getResource("/exhibition_query.json")!!.readText()
			)
		)
		
		val expected = ExhibitionQuery(
			Info(235, 1, 470), listOf(
				ExhibitionItem(
					exhibitionId = 225,
					title = "Colors of the Caucasus",
					beginDate = "1990-06-16",
					endDate = "1990-09-09",
					primaryImageUrl = null
				),
				ExhibitionItem(
					exhibitionId = 249,
					title = "David Smith: \"This work is my identity\"",
					beginDate = "1995-06-03",
					endDate = "1996-05-05",
					primaryImageUrl = "http://nrs.harvard.edu/urn-3:huam:GS04997_dynmc"
				),
			)
		)
		
		val testSubscriber = service.getCurrentExhibitions().test()
		
		testSubscriber.assertNoErrors()
		testSubscriber.assertComplete()
		testSubscriber.assertResult(expected)
	}
	
	@Test
	fun testDetailReceived() {
		webServer.enqueue(
			MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(
				javaClass.getResource("/exhibition_detail.json")!!.readText()
			)
		)
		
		val expected = ExhibitionDetail(
			exhibitionId = 249,
			title = "David Smith: \"This work is my identity\"",
			beginDate = "1995-06-03",
			endDate = "1996-05-05",
			primaryImageUrl = "http://nrs.harvard.edu/urn-3:huam:GS04997_dynmc",
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