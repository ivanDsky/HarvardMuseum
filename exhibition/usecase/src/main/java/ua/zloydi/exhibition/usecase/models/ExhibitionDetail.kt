package ua.zloydi.exhibition.usecase.models

import com.google.gson.annotations.SerializedName

public data class ExhibitionDetail(
	@SerializedName("exhibitionid")
	val exhibitionId: Int,
	@SerializedName("title")
	val title: String,
	@SerializedName("begindate")
	val beginDate: String?,
	@SerializedName("enddate")
	val endDate: String?,
	@SerializedName("primaryimageurl")
	val primaryImageUrl: String?,
	@SerializedName("shortdescription")
	val shortDescription: String?,
	val description: String?,
	val images: List<ImagesItem>?,
	val venues: List<VenuesItem>?,
	val publications: List<PublicationsItem>?
)

public data class VenuesItem(
	@SerializedName("venueid")
	val venueId: Int,
	@SerializedName("begindate")
	val beginDate: String?,
	@SerializedName("enddate")
	val endDate: String?,
	val name: String,
	val country: String?,
	val state: String?,
	val city: String?,
	val address1: String?,
)

public data class PublicationsItem(
	@SerializedName("publicationid")
	val id: Int,
	val title: String,
	@SerializedName("shortdescription")
	val shortDescription: String?,
	val description: String?,
	@SerializedName("publicationplace")
	val place: String?,
	@SerializedName("publicationyear")
	val year: Int?,
	val format: String?,
	@SerializedName("primaryimageurl")
	val primaryImageUrl: String?,
	@SerializedName("publicationdate")
	val publicationDate: String?
)

public data class ImagesItem(
	val date: String?,
	val copyright: String?,
	@SerializedName("imageid")
	val imageId: Int,
	@SerializedName("idsid")
	val idsId: Int?,
	val description: String?,
	val technique: String?,
	@SerializedName("baseimageurl")
	val baseImageUrl: String,
)

